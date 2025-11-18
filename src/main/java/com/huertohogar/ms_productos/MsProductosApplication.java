package com.huertohogar.ms_productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
public class MsProductosApplication { public static void main(String[] args) {
	SpringApplication.run(MsProductosApplication.class, args);
} }

