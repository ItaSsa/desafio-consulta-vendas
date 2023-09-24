package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}
	
	public Page<SaleReportDTO> searchReport(String minDate , String maxDate,String name, Pageable pageable ){
		
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDate2 ;
		LocalDate maxDate2 ;
		
		if  (minDate.isEmpty()) {
			minDate2 = today.minusYears(1L);
		}else {
			minDate2 = LocalDate.parse(minDate);
		}
		
		if (maxDate.isEmpty()) { 
			maxDate2 = today;
		}else {
			 maxDate2 =  LocalDate.parse(maxDate);
		}

		Page<SaleReportDTO> result = repository.searchReport(minDate2,maxDate2, name, pageable);

		return result;
	}
}
