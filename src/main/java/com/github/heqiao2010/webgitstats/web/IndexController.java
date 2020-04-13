package com.github.heqiao2010.webgitstats.web;

import com.github.heqiao2010.webgitstats.entity.GitRepositoryDto;
import com.github.heqiao2010.webgitstats.service.GitRepoService;
import com.github.heqiao2010.webgitstats.web.vo.AddRepoRequest;
import com.github.heqiao2010.webgitstats.web.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class IndexController {
    private final GitRepoService gitRepoService;

    @Autowired
    public IndexController(GitRepoService gitRepoService) {
        this.gitRepoService = gitRepoService;
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model){
        List<GitRepositoryDto> allRepoDto = gitRepoService.findAll();
        model.addAttribute("all", allRepoDto);
        Pair<Boolean, String> result = gitRepoService.checkRequire();
        if (!result.getFirst()) {
            model.addAttribute("errMsg", result.getSecond());
        }
        return "index";
    }

    @PostMapping("/add")
    @ResponseBody
    public BaseResponse addRepo(AddRepoRequest request){
        log.info("request: " + request);
        Pair<Boolean, String> result = gitRepoService.checkRequire();
        if (!result.getFirst()) {
            return BaseResponse.builder()
                    .message("请先安装依赖的组件。")
                    .success(false)
                    .build();
        }
        String message = "添加成功";
        boolean success = true;
        try {
            gitRepoService.addRepo(request);
        } catch (Exception e) {
            message = e.getMessage();
            success = false;
            log.error("addRepo failed!", e);
        }
        return BaseResponse.builder()
                .message(message)
                .success(success)
                .build();
    }

    @DeleteMapping("/del/{id}")
    @ResponseBody
    public BaseResponse delRepo(@PathVariable("id") Long id){
        log.info("delete id: {}", id);
        String message = "删除成功";
        boolean success = true;
        try {
            GitRepositoryDto repo = gitRepoService.findById(id);
            if (null == repo) {
                throw new IllegalArgumentException("no repo found by id " + id);
            }
            if (gitRepoService.isProcessing(repo.getDir())) {
                return BaseResponse.builder()
                        .message("任务正在处理中，请稍后再试")
                        .success(false)
                        .build();
            }
            gitRepoService.deleteById(id, repo.getDir());
        } catch (Exception e) {
            message = e.getMessage();
            success = false;
            log.error("delRepo failed!", e);
        }
        return BaseResponse.builder()
                .message(message)
                .success(success)
                .build();
    }
}
