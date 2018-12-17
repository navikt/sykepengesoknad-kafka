package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum ArbeidssituasjonDTO {
    SELVSTENDIG_NARINGSDRIVENDE,
    FRILANSER,
    ARBEIDSTAKER,
    @JsonEnumDefaultValue UKJENT
}
