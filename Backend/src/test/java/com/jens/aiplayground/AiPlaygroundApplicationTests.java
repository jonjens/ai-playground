package com.jens.aiplayground;

import com.jens.aiplayground.service.EnvService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class AiPlaygroundApplicationTests {

	@Autowired
	EnvService envService;

	@Test
	void contextLoads() {
	}

	@Test
	void testKey(){
		System.out.println(envService.getKey());
	}

}
