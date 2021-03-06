package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import com.github.heqiao2010.webgitstats.service.runner.RepoCloneRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CloneProcessor implements StatsProcessor {

    private final RepoCloneRunner repoCloneRunner;

    @Autowired
    public CloneProcessor(RepoCloneRunner repoCloneRunner) {
        this.repoCloneRunner = repoCloneRunner;
    }

    @Override
    public StatusEnum getStatus() {
        return StatusEnum.CLONING;
    }

    @Override
    public void process(ProcessContext context, ProcessChain chain) {
        try {
            updateRepoStats(context);
            boolean result = repoCloneRunner.doClone(context.getRepo());
            if (result) {
                chain.doChain(context);
            } else {
                context.setProcessSuccess(false);
                context.setFailedMessage("clone failed!");
            }
        } catch (Exception e){
            log.error("error occur when clone", e);
            ErrorProcessor.handleError(context);
        }
    }
}
