package no.nav.syfo.kafka.sykepengesoknadarbeidsledig.dto

data class InntektskildeDTO(
        val type: InntektskildetypeDTO? = null,
        val sykmeldt: Boolean? = null
)
