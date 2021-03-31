package no.nav.syfo.kafka.felles


import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadDTO(
    val id: String,
    val type: SoknadstypeDTO,
    val status: SoknadsstatusDTO,
    val fnr: String,
    val sykmeldingId: String? = null,
    val arbeidsgiver: ArbeidsgiverDTO? = null,
    val arbeidssituasjon: ArbeidssituasjonDTO? = null,
    val korrigerer: String? = null,
    val korrigertAv: String? = null,
    val soktUtenlandsopphold: Boolean? = null,
    val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
    val fom: LocalDate? = null,
    val tom: LocalDate? = null,
    val dodsdato: LocalDate? = null,
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
    val egenmeldtSykmelding: Boolean? = null,
    val harRedusertVenteperiode: Boolean? = null,
    val behandlingsdager: List<LocalDate>? = null,
    val permitteringer: List<PeriodeDTO>? = null,
    val merknaderFraSykmelding: List<MerknadDTO>? = null
)
