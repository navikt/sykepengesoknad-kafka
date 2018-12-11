package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum VisningskriteriumDTO {
    NEI,
    JA,
    CHECKED,
    @JsonEnumDefaultValue UKJENT
}
