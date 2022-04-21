package com.ecommerce.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderTest {
	
	@Test
	public void testEncoderPssword() {
		BCryptPasswordEncoder passworedEncoder = new BCryptPasswordEncoder();
		String rawPassword = "yassine2020";
		String encodedPassword = passworedEncoder.encode(rawPassword);
		
		System.out.println(encodedPassword);
		
		boolean matches = passworedEncoder.matches(rawPassword, encodedPassword);
		
		assertThat(matches).isTrue();
	}

}
