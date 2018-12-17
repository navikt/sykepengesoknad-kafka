package no.nav.syfo.kafka.soknad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SvarDTO {
    String verdi;
}
