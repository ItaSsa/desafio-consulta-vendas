package com.devsuperior.dsmeta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@SpringBootApplication
public class DsmetaApplication  implements CommandLineRunner{

	@Autowired
	private SaleRepository repository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Testes");
		
		List<SaleSummaryProjection> list = repository.searchSummary("2022-01-01", "2022-06-30");
		
		for(SaleSummaryProjection s: list) {
			System.out.println(new SaleSummaryDTO(s));
		}
		
	}


}
