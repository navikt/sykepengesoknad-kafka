package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate

data class PeriodeDTO(
    val fom: LocalDate? = null,
    val tom: LocalDate? = null
)
