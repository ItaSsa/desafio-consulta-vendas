package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryProjection;

public class SaleSummaryDTO {
	
		private String name;
		private Double total;
		
		public SaleSummaryDTO(String name, Double total) {
			this.name = name;
			this.total = total;
		}

		public SaleSummaryDTO(SaleSummaryProjection projection) {
			name = projection.getName();
			total = projection.getTotal();
		}
		
		public String getName() {
			return name;
		}

		public Double getTotal() {
			return total;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("SaleSummaryDTO [name=");
			builder.append(name);
			builder.append(", total=");
			builder.append(total);
			builder.append("]");
			return builder.toString();
		}
		
		
		
		
}
