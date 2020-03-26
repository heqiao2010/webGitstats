package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.StatusEnum;

public interface StatsProcessor {
    StatusEnum getStatus();
    void process(ProcessContext context, ProcessChain chain);
}
