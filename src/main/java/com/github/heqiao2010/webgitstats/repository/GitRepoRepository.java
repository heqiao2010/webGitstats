package com.github.heqiao2010.webgitstats.repository;

import com.github.heqiao2010.webgitstats.entity.GitRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface GitRepoRepository extends PagingAndSortingRepository<GitRepository, Long> {

}
