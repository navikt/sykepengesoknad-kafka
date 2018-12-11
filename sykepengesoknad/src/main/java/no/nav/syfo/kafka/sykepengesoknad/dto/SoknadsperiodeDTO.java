package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SoknadsperiodeDTO {
    LocalDate fom;
    LocalDate tom;
    Integer sykmeldingGrad;
    Integer faktiskGrad;
    Double avtaltTimer;
    Double faktiskTimer;
    SykmeldingstypeDTO sykmeldingtype;
}