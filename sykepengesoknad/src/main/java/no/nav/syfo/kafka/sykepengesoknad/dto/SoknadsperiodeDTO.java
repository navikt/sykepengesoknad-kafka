package no.nav.syfo.kafka.sykepengesoknad.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class SoknadsperiodeDTO {
    LocalDate fom;
    LocalDate tom;
    Integer sykmeldingsgrad;
    Integer faktiskGrad;
    Double avtaltTimer;
    Double faktiskTimer;
    SykmeldingstypeDTO sykmeldingstype;
}
