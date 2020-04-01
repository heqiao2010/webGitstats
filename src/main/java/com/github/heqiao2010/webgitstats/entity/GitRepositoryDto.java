package com.github.heqiao2010.webgitstats.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GitRepositoryDto {
    /**
     * 记录Id
     */
    private Long id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 仓库地址
     */
    private String addr;

    /**
     * 目录地址
     */
    private String dir;

    /**
     * 状态
     */
    private String status;

    public static GitRepositoryDto from(GitRepository gitRepository) {
        return GitRepositoryDto.builder()
                .id(gitRepository.getId())
                .name(gitRepository.getName())
                .addr(gitRepository.getAddr())
                .dir(gitRepository.getDirPath())
                .status(StatusEnum.fromStatus(gitRepository.getStatus()).getDesc())
                .build();
    }
}
