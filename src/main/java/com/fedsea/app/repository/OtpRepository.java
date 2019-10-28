package com.fedsea.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fedsea.app.model.OTP;

@Repository
public interface OtpRepository extends JpaRepository<OTP, Long> {

	OTP findByMobileNumberAndOtp(String mobileNumber, int otp);

}
