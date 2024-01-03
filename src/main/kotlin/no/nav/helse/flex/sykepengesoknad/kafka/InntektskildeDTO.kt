package no.nav.helse.flex.sykepengesoknad.kafka

data class InntektskildeDTO(
    val type: InntektskildetypeDTO? = null,
    val sykmeldt: Boolean? = null,
)
