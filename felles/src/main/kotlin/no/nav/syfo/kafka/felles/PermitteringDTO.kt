package no.nav.syfo.kafka.felles

import java.time.LocalDate

data class PermitteringDTO(
        val fom: LocalDate,
        val tom: LocalDate?
)
