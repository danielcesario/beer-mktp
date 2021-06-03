package br.com.poc.beermktp.core.gateway.rest.controller;

import br.com.poc.beermktp.core.gateway.rest.viewmodel.FreightRequest;
import br.com.poc.beermktp.core.gateway.rest.viewmodel.FreightResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;

@RestController
@RequestMapping(value = "/api/freight")
public class FreightController {

    private final static BigDecimal MIN = BigDecimal.TEN;
    private final static BigDecimal MAX = BigDecimal.valueOf(100);

    @PostMapping
    public ResponseEntity<FreightResponse> calculate(@RequestBody FreightRequest request) {
        final Random random = new Random();
        final BigDecimal value = MIN.add(new BigDecimal(random.nextDouble()).multiply(MAX.subtract(MIN)))
                .setScale(2, RoundingMode.HALF_UP);
        final LocalDate deliveryDate = LocalDate.now().plusDays(random.nextInt(30 - 5) + 5);
        final FreightResponse result = FreightResponse.builder().value(value).deliveryDate(deliveryDate).build();
        return ResponseEntity.ok(result);
    }
}
