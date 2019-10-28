package com.fedsea.app.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.model.Group;
import com.fedsea.app.model.MemberInGroup;
import com.fedsea.app.model.User;
import com.fedsea.app.repository.GroupRepository;
import com.fedsea.app.repository.MemberInGroupRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IGroupsAndCommunitiesService;

@RestController
@RequestMapping("/api/groups")
public class GroupsAndCommunitiesController {

	private static final String GROUP_ADDED_SUCCESSFULLY = "Group added successfully.";

	private static final String GROUP_UPDATED_SUCCESSFULLY = "Group updated successfully.";

	private static final String NO_USER_EXISTS = "No User Exists";

	private static final String NO_GROUP_EXISTS = "No Group Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String INVALIDATE_GROUP_ID = "Group id is not valid";

	private static final String NO_GROUPS_AVAILABLE = "No Groups Available";

	private static final String RESPONSE_SEND_SUCCESSFULLY = "Response Send Successfully";

	private static final String NO_INVITATION_FOUND = "No Invitation Found";

	@Autowired
	private IGroupsAndCommunitiesService groupsAndCommunitiesService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Value("${file.upload-dir}")
	private String imagePath;

	@Autowired
	private MemberInGroupRepository memberInGroupRepository;

//	=========================Api Method For Create Group===========================
	@RequestMapping(value = "/v1/user/{userId}/group", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addGroup(@RequestParam(required = false) MultipartFile groupDP,
			@RequestParam("group") String groupData, @PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(groupData);
			Group group = mapper.treeToValue(node, Group.class);
			if (userId != 0) {
				Optional<User> dbUser = userRepository.findById(userId);
				if (dbUser.isPresent()) {
					groupsAndCommunitiesService.addGroup(group, userId, groupDP);
					if (group.getId() != null) {
						apiResponseDtoBuilder.withMessage(GROUP_ADDED_SUCCESSFULLY).withStatus(HttpStatus.OK)
								.withData(group);
					} else {
						apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST);
					}
				} else {
					apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
		}

		return apiResponseDtoBuilder.build();
	}

//	=========================Api Method For Update Group===========================
	@RequestMapping(value = "/v1/user/{userId}/group/{groupId}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto updateGroup(@RequestParam(required = false) MultipartFile groupDP,
			@RequestParam("group") String groupData, @PathVariable Long userId, @PathVariable Long groupId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(groupData);
			Group group = mapper.treeToValue(node, Group.class);
			if (userId != 0) {
				Optional<User> dbUser = userRepository.findById(userId);
				if (dbUser.isPresent()) {
					if (groupId != 0) {
						Optional<Group> dbGroup = groupRepository.findById(groupId);
						if (dbGroup.isPresent()) {
							groupsAndCommunitiesService.updateGroup(dbGroup, userId, groupDP);
							apiResponseDtoBuilder.withMessage(GROUP_UPDATED_SUCCESSFULLY).withStatus(HttpStatus.OK)
									.withData(group);
						} else {
							apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
						}
					} else {
						apiResponseDtoBuilder.withMessage(INVALIDATE_GROUP_ID).withStatus(HttpStatus.NO_CONTENT);
					}
				} else {
					apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
			}

		} catch (Exception e) {
		}

		return apiResponseDtoBuilder.build();
	}

//	====================Api Method For Add New Member In Group===========================
	@RequestMapping(value = "/v1/user/{userId}/member", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto addNewMemberInGroup(@RequestBody MemberInGroup memberInGroup, @PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				Optional<Group> dbGroup = groupRepository.findById(memberInGroup.getGroupId());
				if (dbGroup.isPresent()) {
					groupsAndCommunitiesService.addMemberInGroup(memberInGroup, userId);
					if (memberInGroup.getId() != null) {
						apiResponseDtoBuilder.withMessage(GROUP_ADDED_SUCCESSFULLY).withStatus(HttpStatus.OK)
								.withData(memberInGroup);
					} else {
						apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.BAD_REQUEST);
					}
				} else {
					apiResponseDtoBuilder.withMessage(NO_GROUP_EXISTS).withStatus(HttpStatus.NO_CONTENT);
				}

			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}

		return apiResponseDtoBuilder.build();
	}

//	====================Api Method For Respons To Invitation Group===========================
	@RequestMapping(value = "/v1/user/{userId}/group/{groupId}/respond", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto respondToInvitationGroup(@RequestBody MemberInGroup memberInGroup, @PathVariable Long userId,
			@PathVariable Long groupId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				Optional<Group> dbGroup = groupRepository.findById(groupId);
				if (dbGroup.isPresent()) {
					MemberInGroup dbMemberInGroup = memberInGroupRepository.findByInvitorIdAndGroupId(userId, groupId);
					if (dbMemberInGroup != null) {
						// groupsAndCommunitiesService.responseToInvitationGroup(dbMemberInGroup,
						// memberInGroup.get);
						apiResponseDtoBuilder.withMessage(RESPONSE_SEND_SUCCESSFULLY).withStatus(HttpStatus.OK)
								.withData(memberInGroup);
					} else {
						apiResponseDtoBuilder.withMessage(NO_INVITATION_FOUND).withStatus(HttpStatus.BAD_REQUEST);
					}
				} else {
					apiResponseDtoBuilder.withMessage(NO_GROUP_EXISTS).withStatus(HttpStatus.NO_CONTENT);
				}

			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}

		return apiResponseDtoBuilder.build();
	}

//	===========================Api Method For Get Groups by user Id===========================
	@RequestMapping(value = "/v1/user/{userId}/groups", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ApiResponseDto getEvents(@PathVariable Long userId) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (userId != 0) {
			Optional<User> dbUser = userRepository.findById(userId);
			if (dbUser.isPresent()) {
				List<Group> listGroups = groupRepository.findByCreatorId(userId);
				if (!listGroups.isEmpty()) {
					apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK).withData(listGroups);
				} else {
					apiResponseDtoBuilder.withMessage(NO_GROUPS_AVAILABLE).withStatus(HttpStatus.NO_CONTENT);
				}
			} else {
				apiResponseDtoBuilder.withMessage(NO_USER_EXISTS).withStatus(HttpStatus.NO_CONTENT);
			}
		} else {
			apiResponseDtoBuilder.withMessage(INVALIDATE_USER_ID).withStatus(HttpStatus.NO_CONTENT);
		}
		return apiResponseDtoBuilder.build();
	}

//	==========================Api Method For Get GroupDP Image===========================
	@RequestMapping(value = "/image/{imageName}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) {
		byte[] image = null;
		try {
			image = Files.readAllBytes(Paths.get(imagePath + "/groups/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
}
