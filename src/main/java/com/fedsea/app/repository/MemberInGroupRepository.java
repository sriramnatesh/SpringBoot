package com.fedsea.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.MemberInGroup;

@Repository
public interface MemberInGroupRepository extends JpaRepository<MemberInGroup, Long> {

	MemberInGroup findByInvitorIdAndGroupId(Long userId, Long groupId);

}
