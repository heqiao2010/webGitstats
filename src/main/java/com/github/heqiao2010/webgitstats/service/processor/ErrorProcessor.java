package com.github.heqiao2010.webgitstats.service.processor;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorProcessor implements StatsProcessor {
    @Override
    public StatusEnum getStatus() {
        return StatusEnum.ERROR;
    }

    @Override
    public void process(ProcessContext context, ProcessChain chain) {
        handleError(context);
    }

    public static void handleError(ProcessContext context) {
        GitRepository repo = context.getRepo();
        repo.setStatus(StatusEnum.ERROR.getStatus());
        context.getGitRepoRepository().save(repo);
        context.setProcessSuccess(false);
        context.getIsProcessing().set(false);
    }
}
