package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum FravarType {
    FERIE,
    PERMISJON,
    UTLANDSOPPHOLD,
    UTDANNING,
    @JsonEnumDefaultValue UNKNOWN
}
