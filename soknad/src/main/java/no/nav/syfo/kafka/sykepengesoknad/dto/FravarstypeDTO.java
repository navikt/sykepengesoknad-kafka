package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum FravarstypeDTO {
    FERIE,
    PERMISJON,
    UTLANDSOPPHOLD,
    UTDANNING_FULLTID,
    UTDANNING_DELTID,
    @JsonEnumDefaultValue UKJENT
}
