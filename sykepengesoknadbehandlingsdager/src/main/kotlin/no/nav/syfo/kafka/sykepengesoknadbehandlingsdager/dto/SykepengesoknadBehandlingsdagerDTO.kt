package no.nav.syfo.kafka.sykepengesoknadbehandlingsdager.dto

import no.nav.syfo.kafka.felles.ArbeidsgiverDTO
import no.nav.syfo.kafka.felles.ArbeidsgiverForskuttererDTO
import no.nav.syfo.kafka.felles.InntektskildeDTO
import no.nav.syfo.kafka.felles.MottakerDTO
import no.nav.syfo.kafka.felles.PeriodeDTO
import no.nav.syfo.kafka.felles.SoknadFellesDTO
import no.nav.syfo.kafka.felles.SykepengesoknadFellesDTO
import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadBehandlingsdagerDTO(
        val soknadFelles: SoknadFellesDTO,
        val sykepengesoknadFelles: SykepengesoknadFellesDTO,
        val arbeidsgiver: ArbeidsgiverDTO? = null,
        val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
        val egenmeldinger: List<PeriodeDTO>? = null,
        val behandlingsdager: List<LocalDate>,
        val papirsykmeldinger: List<PeriodeDTO>? = null,
        val andreInntektskilder: List<InntektskildeDTO>? = null,
        val ettersending: Boolean = false,
        val mottaker: MottakerDTO? = null,
        val sendtArbeidsgiver: LocalDateTime? = null
)
