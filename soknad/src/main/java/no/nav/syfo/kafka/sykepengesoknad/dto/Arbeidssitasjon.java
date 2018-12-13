package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Arbeidssitasjon {
    SELVSTENDIG_NARINGSDRIVENDE,
    FRILANSER,
    ARBEIDSTAKER,
    @JsonEnumDefaultValue UNKNOWN
}
