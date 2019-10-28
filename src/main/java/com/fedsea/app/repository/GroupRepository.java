package com.fedsea.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

	List<Group> findByCreatorId(Long userId);

}
