package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.dto.SaleSummaryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<SaleReportDTO> getReport(String name, String minDate, String maxDate, Pageable pageable) {
		LocalDate maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minLocalDate = today.minusYears(1L);

		if (!maxDate.isEmpty()) {
			maxLocalDate = LocalDate.parse(maxDate, formatter);
		}
		if (!minDate.isEmpty()) {
			minLocalDate = LocalDate.parse(minDate, formatter);
		}

		Page<SaleReportDTO> result = repository.searchReport(name, minLocalDate, maxLocalDate, pageable);
		return result;
	}

	@Transactional(readOnly = true)
	public Page<SaleSummaryDTO> getSummary(String minDate, String maxDate, Pageable pageable) {
		LocalDate maxLocalDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		LocalDate minLocalDate = today.minusYears(1L);

		if (!maxDate.isEmpty()) {
			maxLocalDate = LocalDate.parse(maxDate, formatter);
		}
		if (!minDate.isEmpty()) {
			minLocalDate = LocalDate.parse(minDate, formatter);
		}

		Page<SaleSummaryDTO> result = repository.searchSummary(minLocalDate, maxLocalDate, pageable);
		return result;
	}
}
