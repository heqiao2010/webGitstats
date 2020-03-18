package com.github.heqiao2010.webgitstats.web;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.GitRepositoryDto;
import com.github.heqiao2010.webgitstats.service.GitRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        model.addAttribute("check_result", 0);
        return "index";
    }
}
