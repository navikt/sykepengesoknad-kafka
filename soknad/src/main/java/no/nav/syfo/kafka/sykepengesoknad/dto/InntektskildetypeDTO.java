package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum InntektskildetypeDTO {
    ANDRE_ARBEIDSFORHOLD,
    FRILANSER,
    SELVSTENDIG_NARINGSDRIVENDE,
    SELVSTENDIG_NARINGSDRIVENDE_DAGMAMMA,
    JORDBRUKER_FISKER_REINDRIFTSUTOVER,
    ANNET,
    @JsonEnumDefaultValue UKJENT
}
