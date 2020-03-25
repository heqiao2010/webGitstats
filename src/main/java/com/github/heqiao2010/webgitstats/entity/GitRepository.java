package com.github.heqiao2010.webgitstats.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Tolerate;

import javax.persistence.*;

/**
 * git仓库实体
 */
@Entity
@Getter
@Setter
@Builder
@Table(name = "t_repository")
public class GitRepository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 目录地址
     */
    @Column(name = "dir_path")
    private String dirPath;

    /**
     * 仓库地址
     */
    private String addr;

    /**
     * status
     */
    private int status;

    @Tolerate
    public GitRepository(){}
}
