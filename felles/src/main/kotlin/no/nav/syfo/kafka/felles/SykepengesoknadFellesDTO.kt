package no.nav.syfo.kafka.felles

import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadFellesDTO(
        val sykmeldingId: String,
        val fom: LocalDate,
        val tom: LocalDate,
        val startSyketilfelle: LocalDate,
        val sykmeldingSkrevet: LocalDateTime,
        val soknadsperioder: List<SoknadsperiodeDTO>,
        val arbeidssituasjon: ArbeidssituasjonDTO
)
