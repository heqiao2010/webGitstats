package com.github.heqiao2010.webgitstats.service;

import com.github.heqiao2010.webgitstats.repository.GitStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GitStatsService {
    private final GitStatsRepository gitStatsRepository;

    @Autowired
    public GitStatsService(GitStatsRepository gitStatsRepository) {
        this.gitStatsRepository = gitStatsRepository;
    }
}
