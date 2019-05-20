package no.nav.syfo.kafka.sykepengesoknad.dto

data class InntektskildeDTO(
        val type: InntektskildetypeDTO? = null,
        val sykmeldt: Boolean? = null
)
