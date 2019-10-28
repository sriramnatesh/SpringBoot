package com.fedsea.app.service;

import org.springframework.stereotype.Service;

import com.fedsea.app.model.OTP;

@Service
public interface IOtpService {

	void sendOtp(OTP otp);

}
