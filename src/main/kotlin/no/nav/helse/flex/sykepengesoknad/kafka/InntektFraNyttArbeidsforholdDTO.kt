package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate

data class InntektFraNyttArbeidsforholdDTO(
    val fom: LocalDate,
    val tom: LocalDate,
    val forsteArbeidsdag: LocalDate,
    val forstegangssporsmal: Boolean,
    val belopPerDag: Int,
    val arbeidsstedOrgnummer: String,
    val opplysningspliktigOrgnummer: String,
)
