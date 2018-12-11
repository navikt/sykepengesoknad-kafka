package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Periode {
    public LocalDate fom;
    public LocalDate tom;

}
