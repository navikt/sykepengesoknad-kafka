package no.nav.syfo.kafka.sykepengesoknad.dto

import no.nav.syfo.kafka.interfaces.Soknad
import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadDTO(
        val id: String? = null,
        val type: SoknadstypeDTO? = null,
        val status: SoknadsstatusDTO? = null,
        val aktorId: String? = null,
        val sykmeldingId: String? = null,
        val arbeidsgiver: ArbeidsgiverDTO? = null,
        val arbeidssituasjon: ArbeidssituasjonDTO? = null,
        val korrigerer: String? = null,
        val korrigertAv: String? = null,
        val soktUtenlandsopphold: Boolean? = null,
        val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
        val fom: LocalDate? = null,
        val tom: LocalDate? = null,
        val startSyketilfelle: LocalDate? = null,
        val arbeidGjenopptatt: LocalDate? = null,
        val sykmeldingSkrevet: LocalDateTime? = null,
        val opprettet: LocalDateTime? = null,
        val sendtNav: LocalDateTime? = null,
        val sendtArbeidsgiver: LocalDateTime? = null,
        val egenmeldinger: List<PeriodeDTO>? = null,
        val papirsykmeldinger: List<PeriodeDTO>? = null,
        val fravar: List<FravarDTO>? = null,
        val andreInntektskilder: List<InntektskildeDTO>? = null,
        val soknadsperioder: List<SoknadsperiodeDTO>? = null,
        val sporsmal: List<SporsmalDTO>? = null,
        val avsendertype: AvsendertypeDTO? = null,
        val ettersending: Boolean = false,
        val mottaker: MottakerDTO? = null,
        val fnr: String? = null
) : Soknad
