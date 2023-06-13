package com.media.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media.entity.ContentTypeApiEntity;

public interface ContentTypeApiRepo extends JpaRepository<ContentTypeApiEntity, Integer> {

}
