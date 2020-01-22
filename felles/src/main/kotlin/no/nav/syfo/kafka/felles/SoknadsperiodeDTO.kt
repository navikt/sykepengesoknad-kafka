package no.nav.syfo.kafka.felles

import java.time.LocalDate

data class SoknadsperiodeDTO(
        val fom: LocalDate? = null,
        val tom: LocalDate? = null,
        val sykmeldingsgrad: Int? = null,
        val grad: Int? = null, // Fra gammel SoknadDTO
        val faktiskGrad: Int? = null,
        val avtaltTimer: Double? = null,
        val faktiskTimer: Double? = null,
        val sykmeldingstype: SykmeldingstypeDTO? = null
)
