package no.nav.syfo.kafka.sykepengesoknadbehandlingsdager.dto


import no.nav.syfo.kafka.felles.InntektskildeDTO
import no.nav.syfo.kafka.felles.PeriodeDTO
import no.nav.syfo.kafka.felles.SoknadFellesDTO
import no.nav.syfo.kafka.felles.SykepengesoknadFellesDTO
import java.time.LocalDate

data class SykepengesoknadBehandlingsdagerDTO(
        val soknadFelles: SoknadFellesDTO,
        val sykepengesoknadFelles: SykepengesoknadFellesDTO,
        val egenmeldinger: List<PeriodeDTO>? = null,
        val behandlingsdager: List<LocalDate>,
        val papirsykmeldinger: List<PeriodeDTO>? = null,
        val andreInntektskilder: List<InntektskildeDTO>? = null
)
