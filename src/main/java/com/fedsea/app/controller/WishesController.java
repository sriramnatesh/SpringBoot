package com.fedsea.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.dto.WishesDto;
import com.fedsea.app.model.Chat;
import com.fedsea.app.model.User;
import com.fedsea.app.model.Wishes;
import com.fedsea.app.repository.ChatRepository;
import com.fedsea.app.repository.UserRepository;
import com.fedsea.app.service.IChatService;
import com.fedsea.app.service.IWishesService;

@RestController
@RequestMapping("/api/greetings")
public class WishesController {

	private static final String WISHES_ADD_SUCCESSFULLY = "Wishes added successfully.";

	private static final String NO_USER_EXISTS = "No User Exists";
	
	private static final String PARAMETER_NULL = "Some of Wish Parameters are null";

	private static final String NO_RECEIVER_EXISTS = "No Receiver Exists";

	private static final String INVALIDATE_USER_ID = "User id is not valid";

	private static final String WISHES_DELETED_SUCCESSFULLY = "Wishes deleted successfully";

	private static final String WISHES_EXISTS = "Wishes Exists";
	
	private static final String WISHES_DONT_EXISTS = "Wishes Do not Exists";

	@Autowired
	private IWishesService wishesService;


	/*
	 * =========================Api Method For Add Wishes
	 * =============================
	 */

	@RequestMapping(value="/wishes/add",method=RequestMethod.POST)
	public ApiResponseDto addWishes(@RequestBody WishesDto newwishes) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		
		if (newwishes.getWishesfrom() != null && newwishes.getWishes() != null && newwishes.getUpdatedon() != null
				&& newwishes.getWishesto() != null && newwishes.getWisheddate() != null) {
			wishesService.addWishes(newwishes);

			apiResponseDtoBuilder.withMessage(WISHES_ADD_SUCCESSFULLY).withStatus(HttpStatus.OK);
		} else {
			apiResponseDtoBuilder.withMessage(PARAMETER_NULL).withStatus(HttpStatus.OK);
		}
				
		return apiResponseDtoBuilder.build();
	}

	@RequestMapping(value="/wishes/remove",method=RequestMethod.DELETE)
	public ApiResponseDto deleteWishes(@RequestBody WishesDto rmwishdata) { //Todo Change the remove wishes params 
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if(rmwishdata.getId()!= null &&rmwishdata.getWishesfrom()!=null && rmwishdata.getWishesto()!=null && 
				rmwishdata.getRelationto()!=null ) {
	
			
			wishesService.deleteByIdAndUsernameAndRelationtoAndWishesby(rmwishdata.getId(),rmwishdata.getWishesto(), rmwishdata.getRelationto(), rmwishdata.getWishesfrom());	
			
			apiResponseDtoBuilder.withMessage(WISHES_DELETED_SUCCESSFULLY).withStatus(HttpStatus.OK);

			
		//	apiResponseDtoBuilder.withMessage(WISHES_DONT_EXISTS).withStatus(HttpStatus.OK);
			
		}else {
			
			apiResponseDtoBuilder.withMessage(PARAMETER_NULL).withStatus(HttpStatus.OK);
		}
		return apiResponseDtoBuilder.build();
	}
	
	/*
	 * ================================ Api Method For Get Wishes 
	 * =================================
	 */

	@RequestMapping(value="/wishes/{wishesfrom}/{wishesto}/{relationto}/get",method=RequestMethod.GET)
	public ApiResponseDto getWishes(@PathVariable String wishesfrom, @PathVariable String wishesto, @PathVariable String relationto) throws Exception{
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		
		try {

			if (wishesfrom != null && wishesto != null && relationto != null) {

				List<Wishes> olwishes = wishesService.findByUsernameAndRelationtoAndWishesby(wishesto,relationto,wishesfrom);
				apiResponseDtoBuilder.withMessage(WISHES_EXISTS).withStatus(HttpStatus.OK).withData(olwishes);

			} else {
				apiResponseDtoBuilder.withMessage(PARAMETER_NULL).withStatus(HttpStatus.OK);
			}

		} catch (Exception e) {
			System.out.print("Test");
			
			apiResponseDtoBuilder.withMessage(PARAMETER_NULL).withStatus(HttpStatus.BAD_REQUEST).withData(e);
		}
		return apiResponseDtoBuilder.build();
	}
	
	
	
}
