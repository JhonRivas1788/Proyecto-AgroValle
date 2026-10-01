package com.agrovalle.Proyecto_AgroValle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProyectoAgroValleApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mainArrancaSinErrores() {
		ProyectoAgroValleApplication.main(new String[] {
			"--spring.main.web-application-type=none"
		});
	}
}