package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
@Getter
public class Fravaer {
    LocalDate fom;
    LocalDate tom;
    String type;
}
