package no.nav.helse.flex.sykepengesoknad.normalisert

import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO
import java.time.LocalDateTime

sealed interface SoknadDTO {
    val id: String
    val type: SoknadstypeDTO
    val status: SoknadsstatusDTO
    val sendtNav: LocalDateTime?
    val opprettet: LocalDateTime
    val fnr: String
}
