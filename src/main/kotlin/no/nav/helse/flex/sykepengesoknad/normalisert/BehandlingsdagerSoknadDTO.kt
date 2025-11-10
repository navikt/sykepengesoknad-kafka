package no.nav.helse.flex.sykepengesoknad.normalisert

import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime

data class BehandlingsdagerSoknadDTO(
    override val id: String,
    override val status: SoknadsstatusDTO,
    override val opprettet: LocalDateTime,
    override val sendtNav: LocalDateTime?,
    val fom: LocalDate,
    val tom: LocalDate,
    val sykmeldingUuid: String,
    val aktivertDato: LocalDate,
    val korrigerer: String?,
    val korrigertAv: String?,
    val avbruttDato: LocalDate?,
    val arbeidssituasjon: String,
    val startSykeforlop: LocalDate,
    val arbeidsgiverOrgnummer: String,
    val arbeidsgiverNavn: String,
    val sendtArbeidsgiver: Instant?,
    val sykmeldingSkrevet: Instant?,
    val opprinnelse: String?,
    val avsenderType: String?,
    override val fnr: String,
    val egenmeldtSykmelding: Boolean?,
    val merknaderFraSykmelding: String?,
    val utloptPublisert: Instant?,
    val avbruttFeilinfo: Boolean?,
    val opprettetAvInntektsmelding: Boolean?,
    val sendt: Instant?,
    val utenlandskSykmelding: Boolean?,
    val egenmeldingsdagerFraSykmelding: String?,
    val sykmeldingSignaturDato: Instant?,
    val forstegangSoknad: Boolean?,
    val datastreamMetadata: Map<String, Any>?,
    val tidligereArbeidsgiverOrgnummer: String?,
    val fiskerBlad: String?,
    val selvstendigNaringsdrivende: Boolean?,
) : SoknadDTO {
    override val type = SoknadstypeDTO.BEHANDLINGSDAGER
}
