package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import org.springframework.stereotype.Component;

@Component
public class CloneProcessor implements StatsProcessor {
    @Override
    public StatusEnum getStatus() {
        return StatusEnum.CLONING;
    }

    @Override
    public void process(ProcessContext context, ProcessChain chain) {

    }
}
