package no.nav.syfo.kafka.soknad.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class SvarDTO {
    String verdi;
}
