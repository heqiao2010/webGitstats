package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.StatusEnum;

public interface StatsProcessor {
    StatusEnum getStatus();
    void process(ProcessContext context, ProcessChain chain);

    default void updateRepoStats(ProcessContext context){
        GitRepository repo = context.getRepo();
        repo.setStatus(getStatus().getStatus());
        context.getGitRepoRepository().save(repo);
        context.setCurrentStatus(getStatus());
    }
}
