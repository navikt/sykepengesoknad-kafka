package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadDTO(
    val id: String,
    val type: no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO,
    val status: no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO,
    val fnr: String,
    val sykmeldingId: String? = null,
    val arbeidsgiver: no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverDTO? = null,
    val arbeidssituasjon: no.nav.helse.flex.sykepengesoknad.kafka.ArbeidssituasjonDTO? = null,
    val korrigerer: String? = null,
    val korrigertAv: String? = null,
    val soktUtenlandsopphold: Boolean? = null,
    val arbeidsgiverForskutterer: no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverForskuttererDTO? = null,
    val fom: LocalDate? = null,
    val tom: LocalDate? = null,
    val dodsdato: LocalDate? = null,
    val startSyketilfelle: LocalDate? = null,
    val arbeidGjenopptatt: LocalDate? = null,
    val sykmeldingSkrevet: LocalDateTime? = null,
    val opprettet: LocalDateTime? = null,
    val sendtNav: LocalDateTime? = null,
    val sendtArbeidsgiver: LocalDateTime? = null,
    val egenmeldinger: List<no.nav.helse.flex.sykepengesoknad.kafka.PeriodeDTO>? = null,
    val fravarForSykmeldingen: List<no.nav.helse.flex.sykepengesoknad.kafka.PeriodeDTO>? = null,
    val papirsykmeldinger: List<no.nav.helse.flex.sykepengesoknad.kafka.PeriodeDTO>? = null,
    val fravar: List<no.nav.helse.flex.sykepengesoknad.kafka.FravarDTO>? = null,
    val andreInntektskilder: List<no.nav.helse.flex.sykepengesoknad.kafka.InntektskildeDTO>? = null,
    val soknadsperioder: List<no.nav.helse.flex.sykepengesoknad.kafka.SoknadsperiodeDTO>? = null,
    val sporsmal: List<no.nav.helse.flex.sykepengesoknad.kafka.SporsmalDTO>? = null,
    val avsendertype: no.nav.helse.flex.sykepengesoknad.kafka.AvsendertypeDTO? = null,
    val ettersending: Boolean = false,
    val mottaker: no.nav.helse.flex.sykepengesoknad.kafka.MottakerDTO? = null,
    val egenmeldtSykmelding: Boolean? = null,
    val harRedusertVenteperiode: Boolean? = null,
    val behandlingsdager: List<LocalDate>? = null,
    val permitteringer: List<no.nav.helse.flex.sykepengesoknad.kafka.PeriodeDTO>? = null,
    val merknaderFraSykmelding: List<no.nav.helse.flex.sykepengesoknad.kafka.MerknadDTO>? = null
)
