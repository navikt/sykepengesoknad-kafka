package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate

data class FravarDTO(
    val fom: LocalDate? = null,
    val tom: LocalDate? = null,
    val type: no.nav.helse.flex.sykepengesoknad.kafka.FravarstypeDTO? = null
)
