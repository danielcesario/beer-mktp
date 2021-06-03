package br.com.poc.beermktp.core.gateway.rest.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FreightRequest {

    private String from;
    private String to;
}
