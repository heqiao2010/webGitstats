package com.github.heqiao2010.webgitstats.service;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.GitRepositoryDto;
import com.github.heqiao2010.webgitstats.repository.GitRepoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
