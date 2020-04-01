package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CompleteProcessor implements StatsProcessor {
    @Override
    public StatusEnum getStatus() {
        return StatusEnum.COMPLETE;
    }

    @Override
    public void process(ProcessContext context, ProcessChain chain) {
        updateRepoStats(context);
        context.setProcessSuccess(true);
        context.getIsProcessing().set(false);
    }
}
