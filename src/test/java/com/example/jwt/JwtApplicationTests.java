package com.example.jwt;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtApplicationTests {
	@Autowired
	private JwtProvider jwtProvider;

	@Value("${custom.jwt.secretKey}")
	private String secretKeyPlain;

	@Test
	@DisplayName("시크릿 키 존재 여부 체크")
	void test1() {
		assertThat(secretKeyPlain).isNotNull();
	}

	@Test
	@DisplayName("시크릿 키 플레인을 이용하여 암호화 알고리즘 시크릿 키 객체 생성")
	void test2() {
		String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());

		SecretKey secretKey = Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
		assertThat(secretKey).isNotNull();
	}

	@Test
	@DisplayName("jwtProvider 객체를 활용하여 SecretKey 객체 생성")
	void test3() {
		SecretKey secretKey = jwtProvider.getCachedSecretKey();
		assertThat(secretKey).isNotNull();
	}

	@Test
	@DisplayName("jwtProvider 객체 생성을 한번만 하도록 처리")
	void test4() {
		SecretKey secretKey1 = jwtProvider.getCachedSecretKey();
		SecretKey secretKey2 = jwtProvider.getCachedSecretKey();
		assertThat(secretKey1 == secretKey2).isTrue();
	}

}
