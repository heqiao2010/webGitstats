package com.github.heqiao2010.webgitstats.service;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.GitRepositoryDto;
import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import com.github.heqiao2010.webgitstats.repository.GitRepoRepository;
import com.github.heqiao2010.webgitstats.service.processor.ProcessChain;
import com.github.heqiao2010.webgitstats.service.processor.ProcessContext;
import com.github.heqiao2010.webgitstats.service.runner.BaseRunner;
import com.github.heqiao2010.webgitstats.web.vo.AddRepoRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.exec.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
public class GitRepoService extends BaseRunner {

    private final static String TASK_ALREADY_RUNNING = "有任务正在处理中";
    private final static String EMPTY_DATA = "数据为空";

    private final GitRepoRepository gitRepoRepository;
    private final ProcessChain processChain;
    private final ExecutorService executorService;

    private static ProcessContext context;

    @Autowired
    public GitRepoService(GitRepoRepository gitRepoRepository,
                          ProcessChain processChain) {
        this.gitRepoRepository = gitRepoRepository;
        this.processChain = processChain;
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        log.info("basePath: {}", WebGitStatsUtils.getBasePath());
        context = ProcessContext.builder()
                .currentStatus(StatusEnum.INIT)
                .failedMessage(null)
                .gitRepoRepository(gitRepoRepository)
                .isProcessing(new AtomicBoolean(false))
                .processSuccess(false)
                .build();
    }

    public List<GitRepositoryDto> findAll(){
        List<GitRepositoryDto> allRepoDto = new ArrayList<>();
        Iterable<GitRepository> repoIterator = gitRepoRepository.findAll();
        repoIterator.iterator().forEachRemaining(repo -> allRepoDto.add(GitRepositoryDto.from(repo)));
        return allRepoDto;
    }

    public void addRepo(AddRepoRequest repoRequest) {
        if (null == repoRequest && StringUtils.isEmpty(repoRequest.getAddr())) {
            log.info("empty repoRequest: {}", repoRequest);
            throw new IllegalArgumentException(EMPTY_DATA);
        }
        if (context.getIsProcessing().get()) {
            throw new IllegalStateException(TASK_ALREADY_RUNNING);
        }
        GitRepository repo = repoRequest.toGitRepo();
        gitRepoRepository.save(repo);
        processStats(repo);
    }

    public void deleteById(Long id) {
        if (null == id) {
            log.info("empty id");
            throw new IllegalArgumentException("empty id");
        }
        gitRepoRepository.delete(id);
    }

    public String checkRequire() {
        List<String> required = new ArrayList<>();
        if (!checkGitAvailable()) {
            required.add("git");
        }
        if (!checkPythonAvailable()) {
            required.add("python");
        }
        if (!checkGnuPlotAvailable()) {
            required.add("gnuplot");
        }
        if (!required.isEmpty()) {
            return "未检测到" + String.join(",", required) + "，请先安装这些组件";
        } else {
            return null;
        }
    }

    private boolean checkGitAvailable(){
        CommandLine cmdLine = new CommandLine("git");
        cmdLine.addArguments("--version");
        return runCommand(cmdLine);
    }

    private boolean checkPythonAvailable(){
        CommandLine cmdLine = new CommandLine("python");
        cmdLine.addArguments("-V");
        return runCommand(cmdLine);
    }

    private boolean checkGnuPlotAvailable(){
        CommandLine cmdLine = new CommandLine("gnuplot");
        cmdLine.addArguments("-V");
        return runCommand(cmdLine);
    }

    private void processStats(GitRepository repo){
        context.setRepo(repo);
        context.resetStatus();
        context.getIsProcessing().set(true);
        try {
            executorService.execute(() -> processChain.doChain(context));
        } catch (Exception e) {
            log.error("error occur when processStats", e);
        } finally {
            context.resetStatus();
        }
    }
}
