package no.nav.syfo.kafka.soknad.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
public class SoknadPeriodeDTO {
    LocalDate fom;
    LocalDate tom;
    Integer grad;
}
