package com.fedsea.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fedsea.app.model.Group;
import com.fedsea.app.model.MemberInGroup;
import com.fedsea.app.model.Pages;
import com.fedsea.app.model.Post;
import com.fedsea.app.repository.GroupRepository;
import com.fedsea.app.repository.MemberInGroupRepository;
import com.fedsea.app.repository.PageRepository;

@Service
public class PageServiceImpl implements IPageService {

	
	@Autowired
	public PageRepository pageRepository;
	@Override
	public List<Pages> findByuserId(Long userId) {
		// TODO Auto-generated method stub
		return pageRepository.findByUserId(userId);
	}
	@Override
	public void addPage(Pages page) {
		// TODO Auto-generated method stub
		pageRepository.save(page);
	}

	@Override
	public void updatePage(long pageId, Pages page) {
		page.setId(pageId);
		pageRepository.save(page);
	}
	@Override
	public void deletePage(long pageId) {
		pageRepository.deleteById(pageId);
	}

	
	@Override
	public Pages getPage(long pageId) {
		Optional<Pages> page = pageRepository.findById(pageId);
		if (page.isPresent()) {
			return page.get();
		} else {
			return null;
		}
	}


	

}
