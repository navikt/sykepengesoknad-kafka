package no.nav.syfo.kafka.felles

import java.time.LocalDate

data class PeriodeDTO(
        val fom: LocalDate? = null,
        val tom: LocalDate? = null
)
