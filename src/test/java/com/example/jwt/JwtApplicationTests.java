package com.example.jwt;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtApplicationTests {

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

}
