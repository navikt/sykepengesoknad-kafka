package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum SoknadStatus {
    NY,
    SENDT,
    FREMTIDIG,
    UTKAST_TIL_KORRIGERING,
    KORRIGERT,
    AVBRUTT,
    SLETTET,
    @JsonEnumDefaultValue UKJENT
}
