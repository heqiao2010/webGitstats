package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import com.github.heqiao2010.webgitstats.service.runner.GitStatsRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DoStatsProcessor implements StatsProcessor {

    private final GitStatsRunner gitStatsRunner;

    @Autowired
    public DoStatsProcessor(GitStatsRunner gitStatsRunner) {
        this.gitStatsRunner = gitStatsRunner;
    }

    @Override
    public StatusEnum getStatus() {
        return StatusEnum.IN_STATS;
    }

    @Override
    public void process(ProcessContext context, ProcessChain chain) {
        try {
            updateRepoStats(context);
            boolean result = gitStatsRunner.doStats(context.getRepo());
            if (result) {
                chain.doChain(context);
            } else {
                context.setProcessSuccess(false);
                context.setFailedMessage("doStats failed!");
            }
        } catch (Exception e){
            log.error("error occur when doStats", e);
            ErrorProcessor.handleError(context);
        }
    }
}
