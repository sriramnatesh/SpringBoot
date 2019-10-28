package com.fedsea.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.model.Group;
import com.fedsea.app.model.MemberInGroup;

@Service
public interface IGroupsAndCommunitiesService {

	void addGroup(Group group, Long userId, MultipartFile groupDP);

	void addMemberInGroup(MemberInGroup memberInGroup, Long userId);

	void updateGroup(Optional<Group> dbGroup, Long userId, MultipartFile groupDP);

}
