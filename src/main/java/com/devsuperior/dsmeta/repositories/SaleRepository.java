package com.devsuperior.dsmeta.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public interface SaleRepository extends JpaRepository<Sale, Long> {
	
	@Query("select new  com.devsuperior.dsmeta.dto.SaleReportDTO(obj.id, obj.date,obj.amount,obj.seller.name) "
			+ " from Sale obj "
			+ " where obj.date between :minDate and :maxDate "
			+ " and UPPER(obj.seller.name) like UPPER(CONCAT('%', :name , '%') ) "
          )
	Page<SaleReportDTO> searchReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT seller.name as name, sum(sales.amount) as total "
			+ "FROM TB_SELLER seller join "
			+ "TB_SALES sales on "
			+ "seller.id = sales.seller_id "
			+ "where date between :minDate and :maxDate "
			+ "group by seller.name")
	List<SaleSummaryProjection> searchSummary(String minDate, String  maxDate);

}
