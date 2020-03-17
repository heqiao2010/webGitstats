package com.github.heqiao2010.webgitstats.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 统计记录
 */
@Entity
@Getter
@Setter
@Table(name = "t_gitstats")
public class GitStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * repository 主键
     */
    @Column(name = "r_id")
    private Long rId;

    /**
     * 目录地址
     */
    @Column(name = "dir_path")
    private String dirPath;

    /**
     * status
     */
    private int status;
}
