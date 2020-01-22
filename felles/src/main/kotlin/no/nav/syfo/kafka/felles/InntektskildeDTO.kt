package no.nav.syfo.kafka.felles

data class InntektskildeDTO(
        val type: InntektskildetypeDTO? = null,
        val sykmeldt: Boolean? = null
)
