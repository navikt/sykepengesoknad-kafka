package no.nav.syfo.kafka.felles

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import no.nav.syfo.kafka.felles.ArbeidsgiverDTO
import no.nav.syfo.kafka.felles.ArbeidsgiverForskuttererDTO
import no.nav.syfo.kafka.felles.ArbeidssituasjonDTO
import no.nav.syfo.kafka.felles.AvsendertypeDTO
import no.nav.syfo.kafka.felles.FravarDTO
import no.nav.syfo.kafka.felles.InntektskildeDTO
import no.nav.syfo.kafka.felles.MottakerDTO
import no.nav.syfo.kafka.felles.PeriodeDTO
import no.nav.syfo.kafka.felles.SkjultVerdi
import no.nav.syfo.kafka.felles.SoknadsperiodeDTO
import no.nav.syfo.kafka.felles.SoknadsstatusDTO
import no.nav.syfo.kafka.felles.SoknadstypeDTO
import no.nav.syfo.kafka.felles.SporsmalDTO
import java.time.LocalDate
import java.time.LocalDateTime

@Deprecated(message = "Bruk SykepengesoknadDTO med fnr uten aktorid")
data class DeprecatedSykepengesoknadDTO(
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
        val fodselsnummer: SkjultVerdi<String>?,
        val egenmeldtSykmelding: Boolean? = null,
        val harRedusertVenteperiode: Boolean? = null,
        val behandlingsdager: List<LocalDate>? = null,
        val permitteringer: List<PeriodeDTO>? = null,
        val merknaderFraSykmelding: List<MerknadDTO>? = null
        )  {

    @JsonSerialize
    fun fnr(): String? {
        return this.fodselsnummer?.verdi
    }
}
