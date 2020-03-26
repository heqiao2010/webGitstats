package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
public class ProcessContext {
    private AtomicBoolean isProcessing;
    private Long repoId;
    private boolean processSuccess;
    private String failedMessage;
    private StatusEnum currentStatus;

    public void reset() {
        isProcessing = new AtomicBoolean(false);
        repoId = 0L;
        failedMessage = null;
        processSuccess = false;
        currentStatus = StatusEnum.INIT;
    }
}
