package com.ggarabetti.devjobs_crud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DevjobsCrudApplicationTests {

	@Test
	void contextLoads() {
	}

}
