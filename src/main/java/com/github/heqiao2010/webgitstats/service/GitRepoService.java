package com.github.heqiao2010.webgitstats.service;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.GitRepositoryDto;
import com.github.heqiao2010.webgitstats.repository.GitRepoRepository;
import com.github.heqiao2010.webgitstats.web.vo.AddRepoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GitRepoService {
    private final GitRepoRepository gitRepoRepository;

    @Autowired
    public GitRepoService(GitRepoRepository gitRepoRepository) {
        this.gitRepoRepository = gitRepoRepository;
    }

    public List<GitRepositoryDto> findAll(){
        List<GitRepositoryDto> allRepoDto = new ArrayList<>();
        Iterable<GitRepository> repoIterator = gitRepoRepository.findAll();
        repoIterator.iterator().forEachRemaining(repo -> allRepoDto.add(GitRepositoryDto.from(repo)));
        return allRepoDto;
    }

    public void addRepo(AddRepoRequest repoRequest) {
        if (null == repoRequest && StringUtils.isEmpty(repoRequest.getAddr())) {
            log.info("empty repoRequest: {}", repoRequest);
            throw new IllegalArgumentException("empty repoRequest");
        }
        gitRepoRepository.save(repoRequest.toGitRepo());
    }

    public void deleteById(Long id) {
        if (null == id) {
            log.info("empty id");
            throw new IllegalArgumentException("empty id");
        }
        gitRepoRepository.delete(id);
    }

    public String checkRequire() {
        //"依赖gnuplot,python等组件"
        return null;
    }
}
