package no.nav.syfo.kafka.soknad.dto

import java.time.LocalDate

data class SoknadPeriodeDTO(
        val fom: LocalDate? = null,
        val tom: LocalDate? = null,
        val grad: Int? = null,
        val antallBehandlingsdagerUke: Int? = null
)
