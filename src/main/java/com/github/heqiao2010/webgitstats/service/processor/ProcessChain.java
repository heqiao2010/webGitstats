package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ProcessChain {

    private final Map<StatusEnum, StatsProcessor> processorMap =
            new ConcurrentHashMap<>(ensureMapCapacity(StatusEnum.values().length));

    @Autowired
    public ProcessChain(List<StatsProcessor> processorList) {
        processorList.forEach(processor -> processorMap.put(processor.getStatus(), processor));
    }

    public void doChain(ProcessContext context) {
        StatusEnum nextStatus = context.getCurrentStatus().nextStatus();
        if (null == nextStatus){
            return;
        }
        StatsProcessor processor = processorMap.get(nextStatus);
        if(null != processor){
            processor.process(context, this);
        } else {
            log.warn("no processor found by status: {}", nextStatus);
        }
    }

    private static int ensureMapCapacity(int elementSize) {
        return Float.valueOf(elementSize / 0.75f).intValue() + 1;
    }
}
