package com.fedsea.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.model.Group;
import com.fedsea.app.model.MemberInGroup;
import com.fedsea.app.model.Pages;
import com.fedsea.app.model.Post;

@Service
public interface IPageService {
	/*
	 * void addGroup(Group group, Long userId, MultipartFile groupDP);
	 * 
	 * void addMemberInGroup(MemberInGroup memberInGroup, Long userId);
	 * 
	 * void updateGroup(Optional<Group> dbGroup, Long userId, MultipartFile
	 * groupDP);
	 */
	
	void addPage(Pages page);
	
	void updatePage(long Id, Pages page);

	void deletePage(long pageId);

	Pages getPage(long pageId);
	
	List<Pages> findByuserId(Long userId);

}
