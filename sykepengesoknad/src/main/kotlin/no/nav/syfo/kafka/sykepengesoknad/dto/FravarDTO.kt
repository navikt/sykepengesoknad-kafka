package no.nav.syfo.kafka.sykepengesoknad.dto

import java.time.LocalDate

data class FravarDTO(
        val fom: LocalDate? = null,
        val tom: LocalDate? = null,
        val type: FravarstypeDTO? = null
)
