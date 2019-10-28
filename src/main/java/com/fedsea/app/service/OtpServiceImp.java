package com.fedsea.app.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fedsea.app.model.OTP;
import com.fedsea.app.repository.OtpRepository;

@Service
public class OtpServiceImp implements IOtpService {

	@Autowired
	private OtpRepository otpRepository;

	@Override
	public void sendOtp(OTP otp) {
		int randomOTP = generateOTP(4);
		otp.setOtp(randomOTP);
		otpRepository.save(otp);
	}
	
	public int generateOTP(int len) {
		System.out.println("Generating OTP using random() : ");
		System.out.print("You OTP is : ");

		// Using numeric values
		String numbers = "0123456789";

		// Using random method
		Random rndm_method = new Random();

		String otpString = "";

		for (int i = 0; i < len; i++) {
			// Use of charAt() method : to get character value
			// Use of nextInt() as it is scanning the value as int
			otpString += numbers.charAt(rndm_method.nextInt(numbers.length()));
		}
		int otp = Integer.parseInt(otpString);
		return otp;
	}
}
