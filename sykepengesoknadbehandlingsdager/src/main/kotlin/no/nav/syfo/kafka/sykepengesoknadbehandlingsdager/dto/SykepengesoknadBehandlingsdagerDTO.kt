package no.nav.syfo.kafka.sykepengesoknadbehandlingsdager.dto

import no.nav.syfo.kafka.interfaces.Soknad
import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadBehandlingsdagerDTO(
        val id: String,
        val sykmeldingId: String,
        val aktorId: String,
        val fnr: String,
        val status: SoknadsstatusDTO,
        val fom: LocalDate,
        val tom: LocalDate,
        val startSyketilfelle: LocalDate,
        val sykmeldingSkrevet: LocalDateTime,
        val opprettet: LocalDateTime,
        val soknadsperioder: List<SoknadsperiodeDTO>,
        val sporsmal: List<SporsmalDTO>,
        val arbeidsgiver: ArbeidsgiverDTO? = null,
        val arbeidssituasjon: ArbeidssituasjonDTO,
        val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
        val korrigerer: String? = null,
        val korrigertAv: String? = null,
        val egenmeldinger: List<PeriodeDTO>? = null,
        val behandlingsdager: List<LocalDate>,
        val papirsykmeldinger: List<PeriodeDTO>? = null,
        val andreInntektskilder: List<InntektskildeDTO>? = null,
        val avsendertype: AvsendertypeDTO? = null,
        val ettersending: Boolean = false,
        val mottaker: MottakerDTO? = null,
        val sendtNav: LocalDateTime? = null,
        val sendtArbeidsgiver: LocalDateTime? = null
) : Soknad
