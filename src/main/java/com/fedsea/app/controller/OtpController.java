package com.fedsea.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fedsea.app.dto.ApiResponseDto;
import com.fedsea.app.dto.ApiResponseDto.ApiResponseDtoBuilder;
import com.fedsea.app.model.OTP;
import com.fedsea.app.repository.OtpRepository;
import com.fedsea.app.service.IOtpService;

@RestController
@RequestMapping("/api/register/")
public class OtpController {

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private IOtpService otpService;

//=========================Api Method For Valid Mobile Number=====================
	@RequestMapping(value = "/validateMobileNumber", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ApiResponseDto validateMobileNumber(@RequestBody OTP otp) {
		ApiResponseDtoBuilder apiResponseDtoBuilder = new ApiResponseDtoBuilder();
		if (otp.getOtp() == 0) {
			otpService.sendOtp(otp);
			apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK);
		} else {
			OTP validOTP = otpRepository.findByMobileNumberAndOtp(otp.getMobileNumber(), otp.getOtp());
			if (validOTP != null) {
				otp.setStatus(1);
				otpRepository.save(otp);
				apiResponseDtoBuilder.withMessage("success").withStatus(HttpStatus.OK);
			} else {
				apiResponseDtoBuilder.withMessage("fail").withStatus(HttpStatus.NOT_FOUND);
			}
		}
		return apiResponseDtoBuilder.build();
	}
}
