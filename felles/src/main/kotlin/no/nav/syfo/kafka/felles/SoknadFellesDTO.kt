package no.nav.syfo.kafka.felles

import java.time.LocalDateTime

data class SoknadFellesDTO(
        val id: String,
        val aktorId: String,
        val fodselsnummer: SkjultVerdi<String>?,
        val status: SoknadsstatusDTO,
        val sendtNav: LocalDateTime? = null,
        val korrigerer: String? = null,
        val korrigertAv: String? = null,
        val opprettet: LocalDateTime,
        val avsendertype: AvsendertypeDTO? = null,
        val sporsmal: List<SporsmalDTO>
)
