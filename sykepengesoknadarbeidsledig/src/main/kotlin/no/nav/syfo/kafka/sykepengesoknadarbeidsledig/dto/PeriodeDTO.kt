package no.nav.syfo.kafka.sykepengesoknadarbeidsledig.dto

import java.time.LocalDate

data class PeriodeDTO(
        val fom: LocalDate? = null,
        val tom: LocalDate? = null
)
