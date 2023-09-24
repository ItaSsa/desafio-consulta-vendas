package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;
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

		Page<SaleReportDTO> result = repository.searchReport(
					calcMinDateDefaul(minDate),
					calcMaxDateDefaul(maxDate),
					name, pageable);

		return result;
	}
	
	
	public List<SaleSummaryDTO> searchSummary(String minDate, String maxDate){

		List<SaleSummaryProjection> list = repository.searchSummary(calcMinDateDefaul(minDate).toString(), 
																	calcMaxDateDefaul(maxDate).toString());
		
		return list.stream().map(x -> new SaleSummaryDTO(x)).toList();
	}
	
	private LocalDate calcMinDateDefaul(String minDate) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minDate2 ;
		
		if  (minDate.isEmpty()) {
			minDate2 = today.minusYears(1L);
		}else {
			minDate2 = LocalDate.parse(minDate);
		}
		return minDate2 ;
	}
	
	private LocalDate calcMaxDateDefaul(String maxDate) {

		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate maxDate2 ;
		
		if (maxDate.isEmpty()) { 
			maxDate2 = today;
		}else {
			 maxDate2 =  LocalDate.parse(maxDate);
		}
		return maxDate2 ;
	
	}
		
}
