package org.elsys_bg.ElectronicsRepair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ElectronicsRepairApplication{
	public static void main(String[] args){
		SpringApplication.run(ElectronicsRepairApplication.class, args);
	}
}