package no.nav.syfo.kafka.sykepengesoknadbehandlingsdager.dto

data class InntektskildeDTO(
        val type: InntektskildetypeDTO? = null,
        val sykmeldt: Boolean? = null
)
