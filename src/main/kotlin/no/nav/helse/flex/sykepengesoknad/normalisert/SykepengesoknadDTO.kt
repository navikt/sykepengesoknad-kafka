package no.nav.helse.flex.sykepengesoknad.normalisert

import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverDTO
import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverForskuttererDTO
import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidssituasjonDTO
import no.nav.helse.flex.sykepengesoknad.kafka.AvsendertypeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.FiskerBladDTO
import no.nav.helse.flex.sykepengesoknad.kafka.FravarDTO
import no.nav.helse.flex.sykepengesoknad.kafka.InntektFraNyttArbeidsforholdDTO
import no.nav.helse.flex.sykepengesoknad.kafka.InntektskildeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.MerknadDTO
import no.nav.helse.flex.sykepengesoknad.kafka.MottakerDTO
import no.nav.helse.flex.sykepengesoknad.kafka.PeriodeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SelvstendigNaringsdrivendeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsperiodeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SporsmalDTO
import java.time.LocalDate
import java.time.LocalDateTime

data class SykepengesoknadDTO(
    override val id: String,
    override val type: SoknadstypeDTO,
    override val status: SoknadsstatusDTO,
    override val fnr: String,
    override val opprettet: LocalDateTime,
    override val sendtNav: LocalDateTime? = null,
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
    val friskmeldt: LocalDate? = null,
    val sykmeldingSkrevet: LocalDateTime? = null,
    val opprinneligSendt: LocalDateTime? = null,
    val sendtArbeidsgiver: LocalDateTime? = null,
    val egenmeldinger: List<PeriodeDTO>? = null,
    val fravarForSykmeldingen: List<PeriodeDTO>? = null,
    val papirsykmeldinger: List<PeriodeDTO>? = null,
    val fravar: List<FravarDTO>? = null,
    val andreInntektskilder: List<InntektskildeDTO>? = null,
    val soknadsperioder: List<SoknadsperiodeDTO>? = null,
    val sporsmal: List<SporsmalDTO>? = null,
    val avsendertype: AvsendertypeDTO? = null,
    val ettersending: Boolean = false,
    val mottaker: MottakerDTO? = null,
    val egenmeldtSykmelding: Boolean? = null,
    val yrkesskade: Boolean? = null,
    val arbeidUtenforNorge: Boolean? = null,
    val harRedusertVenteperiode: Boolean? = null,
    val behandlingsdager: List<LocalDate>? = null,
    val permitteringer: List<PeriodeDTO>? = null,
    val merknaderFraSykmelding: List<MerknadDTO>? = null,
    val egenmeldingsdagerFraSykmelding: List<LocalDate>? = null,
    val merknader: List<String>? = null,
    val sendTilGosys: Boolean? = null,
    val utenlandskSykmelding: Boolean? = null,
    val medlemskapVurdering: String? = null,
    val forstegangssoknad: Boolean? = null,
    val tidligereArbeidsgiverOrgnummer: String? = null,
    val fiskerBlad: FiskerBladDTO? = null,
    val inntektFraNyttArbeidsforhold: List<InntektFraNyttArbeidsforholdDTO>? = null,
    val selvstendigNaringsdrivende: SelvstendigNaringsdrivendeDTO? = null,
    val friskTilArbeidVedtakId: String? = null,
    val friskTilArbeidVedtakPeriode: String? = null,
    val fortsattArbeidssoker: Boolean? = null,
    val inntektUnderveis: Boolean? = null,
    val ignorerArbeidssokerregister: Boolean? = null,
) : SoknadDTO {
    init {
        val sykepengesoknadTyper =
            setOf(
                SoknadstypeDTO.ANNET_ARBEIDSFORHOLD,
                SoknadstypeDTO.ARBEIDSLEDIG,
                SoknadstypeDTO.ARBEIDSTAKERE,
                SoknadstypeDTO.SELVSTENDIGE_OG_FRILANSERE,
            )
        require(
            type in sykepengesoknadTyper,
        ) { "Ugjyldig type $type for sykepengesoknad. Må være en av: $sykepengesoknadTyper" }
    }
}
