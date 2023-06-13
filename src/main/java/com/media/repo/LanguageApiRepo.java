package com.media.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.media.entity.LanguageApiEntity;

public interface LanguageApiRepo extends JpaRepository<LanguageApiEntity, Integer> {

}
