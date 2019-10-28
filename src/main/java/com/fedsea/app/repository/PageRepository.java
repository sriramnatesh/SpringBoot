package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.Pages;

@Repository
public interface PageRepository extends JpaRepository<Pages, Long> {

	List<Pages> findByUserId(Long userId);

}
