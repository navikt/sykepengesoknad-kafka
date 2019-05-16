package no.nav.syfo.kafka.sykepengesoknad.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class PeriodeDTO {
    public LocalDate fom;
    public LocalDate tom;
}
