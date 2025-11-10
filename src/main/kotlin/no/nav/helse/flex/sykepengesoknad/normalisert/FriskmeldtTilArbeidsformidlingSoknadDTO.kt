package no.nav.helse.flex.sykepengesoknad.normalisert

import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverDTO
import no.nav.helse.flex.sykepengesoknad.kafka.ArbeidsgiverForskuttererDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO
import java.time.LocalDate
import java.time.LocalDateTime

data class FriskmeldtTilArbeidsformidlingSoknadDTO(
    override val id: String,
    override val status: SoknadsstatusDTO,
    override val fnr: String,
    override val opprettet: LocalDateTime,
    override val sendtNav: LocalDateTime? = null,
    val arbeidsgiver: ArbeidsgiverDTO? = null,
    val korrigerer: String? = null,
    val korrigertAv: String? = null,
    val soktUtenlandsopphold: Boolean? = null,
    val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
    val fom: LocalDate? = null,
    val tom: LocalDate? = null,
    val friskTilArbeidVedtakId: String? = null,
    val friskTilArbeidVedtakPeriode: String? = null,
    val fortsattArbeidssoker: Boolean? = null,
    val inntektUnderveis: Boolean? = null,
    val ignorerArbeidssokerregister: Boolean? = null,
) : SoknadDTO {
    override val type: SoknadstypeDTO = SoknadstypeDTO.FRISKMELDT_TIL_ARBEIDSFORMIDLING
}
