package no.nav.syfo.kafka.felles

import java.time.LocalDateTime

data class SoknadFellesDTO(
        val id: String,
        val aktorId: String,
        val fodselsnummer: SkjultVerdi<String>? = null,
        val status: SoknadsstatusDTO,
        val sendtNav: LocalDateTime? = null,
        val korrigerer: String? = null,
        val korrigertAv: String? = null,
        val opprettet: LocalDateTime,
        val avsendertype: AvsendertypeDTO? = null,
        val sporsmal: List<SporsmalDTO>,
        val arbeidsgiver: ArbeidsgiverDTO? = null,
        val arbeidsgiverForskutterer: ArbeidsgiverForskuttererDTO? = null,
        val ettersending: Boolean = false,
        val mottaker: MottakerDTO? = null,
        val sendtArbeidsgiver: LocalDateTime? = null,
        val arbeidssituasjon: ArbeidssituasjonDTO? = null,
        val egenmeldtSykmelding: Boolean? = null
)
