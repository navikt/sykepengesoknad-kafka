package no.nav.helse.flex.sykepengesoknad.kafka

data class SelvstendigNaringsdrivendeDTO(
    val organisasjon: Organisasjon,
)

data class Organisasjon(
    val orgnummer: String,
    val orgnavn: String,
    val organisasjonsform: String,
)
