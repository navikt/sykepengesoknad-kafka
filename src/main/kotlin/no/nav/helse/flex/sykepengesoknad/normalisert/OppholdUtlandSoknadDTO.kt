package no.nav.helse.flex.sykepengesoknad.normalisert

import no.nav.helse.flex.sykepengesoknad.kafka.AvsendertypeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SporsmalDTO
import java.time.LocalDate
import java.time.LocalDateTime

data class OppholdUtlandSoknadDTO(
    override val id: String,
    override val status: SoknadsstatusDTO,
    override val fnr: String,
    override val sendtNav: LocalDateTime? = null,
    override val opprettet: LocalDateTime,
    val avbruttDato: LocalDate?,
    val avsenderType: AvsendertypeDTO? = null,
    val sporsmal: List<SporsmalDTO> = emptyList(),
) : SoknadDTO {
    override val type = SoknadstypeDTO.OPPHOLD_UTLAND
}
