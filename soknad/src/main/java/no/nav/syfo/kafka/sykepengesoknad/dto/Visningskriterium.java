package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Visningskriterium {
    NEI,
    JA,
    CHECKED,
    @JsonEnumDefaultValue UKJENT
}
