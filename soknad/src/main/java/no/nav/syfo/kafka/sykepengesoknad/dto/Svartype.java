package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Svartype {
    JA_NEI,
    CHECKBOX,
    CHECKBOX_GRUPPE,
    CHECKBOX_PANEL,
    DATO,
    PERIODE,
    PERIODER,
    TIMER,
    FRITEKST,
    IKKE_RELEVANT,
    PROSENT,
    RADIO_GRUPPE,
    RADIO_GRUPPE_TIMER_PROSENT,
    RADIO,
    TALL,
    @JsonEnumDefaultValue UKJENT
}
