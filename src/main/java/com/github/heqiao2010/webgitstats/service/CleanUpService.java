package com.github.heqiao2010.webgitstats.service;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.repository.GitRepoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CleanUpService {
    private static final String CLEAN_CRON = "0 0 23 * * ?";

    private final GitRepoRepository gitRepoRepository;
    private final GitRepoService gitRepoService;

    @Autowired
    public CleanUpService(GitRepoRepository gitRepoRepository,
                          GitRepoService gitRepoService) {
        this.gitRepoRepository = gitRepoRepository;
        this.gitRepoService = gitRepoService;
    }

    @Scheduled(cron = CLEAN_CRON)
    public void clean() {
        Pageable page = new PageRequest(1, GitRepoService.PAGE_SIZE, Sort.Direction.DESC, GitRepository.FILED_CREATE_TIME);
        Iterable<GitRepository> repoIterator;
        do {
            repoIterator = gitRepoRepository.findAll(page);
            repoIterator.forEach(gitRepo -> {
                try {
                    gitRepoService.deleteById(gitRepo.getId(), gitRepo.getDirPath());
                    log.error("clean " + gitRepo + " done!");
                } catch (Exception e) {
                    log.error("delete " + gitRepo + " failed!", e);
                }
            });
            page = page.next();
        } while(repoIterator.iterator().hasNext());
    }
}
