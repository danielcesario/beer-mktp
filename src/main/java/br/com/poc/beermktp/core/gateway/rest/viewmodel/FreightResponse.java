package br.com.poc.beermktp.core.gateway.rest.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreightResponse {

    private BigDecimal value;
    private LocalDate deliveryDate;

}
