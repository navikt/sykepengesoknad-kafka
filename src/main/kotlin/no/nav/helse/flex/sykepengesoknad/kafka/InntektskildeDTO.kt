package no.nav.helse.flex.sykepengesoknad.kafka

data class InntektskildeDTO(
    val type: no.nav.helse.flex.sykepengesoknad.kafka.InntektskildetypeDTO? = null,
    val sykmeldt: Boolean? = null
)
