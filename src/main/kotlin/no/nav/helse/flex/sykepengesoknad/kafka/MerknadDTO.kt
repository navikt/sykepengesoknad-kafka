package no.nav.helse.flex.sykepengesoknad.kafka

data class MerknadDTO(
    val type: String,
    val beskrivelse: String? = null,
)
