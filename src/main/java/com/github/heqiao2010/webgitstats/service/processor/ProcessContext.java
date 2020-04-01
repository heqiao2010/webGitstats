package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import com.github.heqiao2010.webgitstats.repository.GitRepoRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;

@Getter
@Setter
@Builder
public class ProcessContext {
    private AtomicBoolean isProcessing;
    private GitRepository repo;
    private boolean processSuccess;
    private String failedMessage;
    private StatusEnum currentStatus;

    private GitRepoRepository gitRepoRepository;

    public void resetStatus() {
        isProcessing = new AtomicBoolean(false);
        failedMessage = null;
        processSuccess = false;
        currentStatus = StatusEnum.INIT;
    }
}
