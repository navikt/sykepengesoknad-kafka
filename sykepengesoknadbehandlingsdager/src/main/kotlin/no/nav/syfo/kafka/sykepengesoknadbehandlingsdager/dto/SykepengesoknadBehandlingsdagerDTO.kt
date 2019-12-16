package no.nav.syfo.kafka.sykepengesoknadbehandlingsdager.dto

import no.nav.syfo.kafka.interfaces.Soknad
import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadBehandlingsdagerDTO(
        val id: String,
        val status: SoknadsstatusDTO,
        val aktorId: String,
        val sykmeldingId: String,
        val arbeidsgiver: ArbeidsgiverDTO? = null,
        val arbeidssituasjon: ArbeidssituasjonDTO,
        val korrigerer: String? = null,
        val korrigertAv: String? = null,
        val soktUtenlandsopphold: Boolean? = null,
        val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
        val fom: LocalDate,
        val tom: LocalDate,
        val startSyketilfelle: LocalDate,
        val arbeidGjenopptatt: LocalDate? = null,
        val sykmeldingSkrevet: LocalDateTime,
        val opprettet: LocalDateTime,
        val sendtNav: LocalDateTime? = null,
        val sendtArbeidsgiver: LocalDateTime? = null,
        val egenmeldinger: List<PeriodeDTO>? = null,
        val papirsykmeldinger: List<PeriodeDTO>? = null,
        val fravar: List<FravarDTO>? = null,
        val andreInntektskilder: List<InntektskildeDTO>? = null,
        val soknadsperioder: List<SoknadsperiodeDTO>,
        val sporsmal: List<SporsmalDTO>,
        val avsendertype: AvsendertypeDTO? = null,
        val ettersending: Boolean = false,
        val mottaker: MottakerDTO? = null,
        val fnr: String
) : Soknad
