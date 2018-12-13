package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Visningskritere {
    NEI,
    JA,
    CHECKED,
    @JsonEnumDefaultValue UNKNOWN
}
