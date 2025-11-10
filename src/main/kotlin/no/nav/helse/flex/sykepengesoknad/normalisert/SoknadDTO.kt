package no.nav.helse.flex.sykepengesoknad.normalisert

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO
import java.time.LocalDateTime

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
    visible = true,
)
@JsonSubTypes(
    JsonSubTypes.Type(value = BehandlingsdagerSoknadDTO::class, name = "BEHANDLINGSDAGER"),
    JsonSubTypes.Type(value = FriskmeldtTilArbeidsformidlingSoknadDTO::class, name = "FRISKMELDT_TIL_ARBEIDSFORMIDLING"),
    JsonSubTypes.Type(value = GradertReisetilskuddSoknadDTO::class, name = "GRADERT_REISETILSKUDD"),
    JsonSubTypes.Type(value = OppholdUtlandSoknadDTO::class, name = "OPPHOLD_UTLAND"),
    JsonSubTypes.Type(value = ReisetilskuddSoknadDTO::class, name = "REISETILSKUDD"),
    JsonSubTypes.Type(value = SykepengesoknadDTO::class, name = "ANNET_ARBEIDSFORHOLD"),
    JsonSubTypes.Type(value = SykepengesoknadDTO::class, name = "ARBEIDSLEDIG"),
    JsonSubTypes.Type(value = SykepengesoknadDTO::class, name = "ARBEIDSTAKERE"),
    JsonSubTypes.Type(value = SykepengesoknadDTO::class, name = "SELVSTENDIGE_OG_FRILANSERE"),
)
sealed interface SoknadDTO {
    val id: String
    val type: SoknadstypeDTO
    val status: SoknadsstatusDTO
    val sendtNav: LocalDateTime?
    val opprettet: LocalDateTime
    val fnr: String
}
