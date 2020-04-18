package com.github.heqiao2010.webgitstats.web.vo;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import com.github.heqiao2010.webgitstats.entity.StatusEnum;
import lombok.*;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRepoRequest {
    private String addr;

    public GitRepository toGitRepo() {
        return GitRepository.builder()
                .addr(getAddr())
                .dirPath(UUID.randomUUID().toString())
                .name(getRepoNameFromAddr(getAddr()))
                .status(StatusEnum.INIT.getStatus())
                .createTime(System.currentTimeMillis())
                .build();
    }

    private static String getRepoNameFromAddr(String addr) {
        if (StringUtils.isEmpty(addr)) {
            return "UNDEFINE";
        }
        int index = addr.lastIndexOf('/');
        if (index == -1){
            return addr;
        } else {
            String repoName = addr.substring(index + 1);
            if (repoName.endsWith(".git") && repoName.length() > 4) {
                return repoName.substring(0, repoName.length() - 4);
            } else {
                return repoName;
            }
        }
    }
}
