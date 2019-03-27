package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum MottakerDTO {
    NAV,
    ARBEIDSGIVER,
    ARBEIDSGIVER_OG_NAV,
    @JsonEnumDefaultValue UKJENT
}
