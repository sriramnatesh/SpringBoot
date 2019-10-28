package com.fedsea.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.model.Group;
import com.fedsea.app.model.MemberInGroup;
import com.fedsea.app.repository.GroupRepository;
import com.fedsea.app.repository.MemberInGroupRepository;

@Service
public class GroupsAndCommunitiesServiceImpl implements IGroupsAndCommunitiesService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private MemberInGroupRepository memberInGroupRepository;

	@Override
	public void addGroup(Group group, Long userId, MultipartFile groupDP) {
		group.setCreatorId(userId);
		groupRepository.save(group);
		if (groupDP != null) {
			String groupDpUrl = fileStorageService.storeFile(groupDP, String.valueOf(group.getId()), "group_dp",
					"groups");
			group.setGroupDP(groupDpUrl);
		}
	}

	@Override
	public void addMemberInGroup(MemberInGroup memberInGroup, Long userId) {
		memberInGroup.setUserId(userId);
		memberInGroupRepository.save(memberInGroup);
	}

	@Override
	public void updateGroup(Optional<Group> dbGroup, Long userId, MultipartFile groupDP) {
		
	}
}
