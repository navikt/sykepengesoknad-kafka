package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate

data class InntektFraNyttArbeidsforholdDTO(
    val fom: LocalDate,
    val tom: LocalDate,
    val harJobbet: Boolean,
    val belop: Int?,
    val arbeidsstedOrgnummer: String,
    val opplysningspliktigOrgnummer: String,
)
