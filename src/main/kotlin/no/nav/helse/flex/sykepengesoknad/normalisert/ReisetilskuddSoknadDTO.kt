package no.nav.helse.flex.sykepengesoknad.normalisert

import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverDTO
import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverForskuttererDTO
import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidssituasjonDTO
import no.nav.helse.flex.sykepengesoknad.kafka.AvsendertypeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.FiskerBladDTO
import no.nav.helse.flex.sykepengesoknad.kafka.FravarDTO
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

data class ReisetilskuddSoknadDTO(
    override val id: String,
    override val status: SoknadsstatusDTO,
    override val fnr: String,
    override val opprettet: LocalDateTime,
    override val sendtNav: LocalDateTime? = null,
    val fom: LocalDate? = null,
    val tom: LocalDate? = null,
    val sykmeldingId: String? = null,
    val arbeidsgiver: ArbeidsgiverDTO? = null,
    val arbeidssituasjon: ArbeidssituasjonDTO? = null,
    val korrigerer: String? = null,
    val korrigertAv: String? = null,
    val soktUtenlandsopphold: Boolean? = null,
    val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
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
    val permitteringer: List<PeriodeDTO>? = null,
    val merknaderFraSykmelding: List<MerknadDTO>? = null,
    val egenmeldingsdagerFraSykmelding: List<LocalDate>? = null,
    val merknader: List<String>? = null,
    val sendTilGosys: Boolean? = null,
    val utenlandskSykmelding: Boolean? = null,
    val forstegangssoknad: Boolean? = null,
    val tidligereArbeidsgiverOrgnummer: String? = null,
    val fiskerBlad: FiskerBladDTO? = null,
    val selvstendigNaringsdrivende: SelvstendigNaringsdrivendeDTO? = null,
) : SoknadDTO {
    override val type = SoknadstypeDTO.REISETILSKUDD
}
