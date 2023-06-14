package com.media.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media.entity.ContentEntity;

public interface ContentRepo extends JpaRepository<ContentEntity, Integer> {

}
