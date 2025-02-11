package no.nav.helse.flex.sykepengesoknad.kafka

data class SelvstendigNaringsdrivendeDTO(
    val roller: List<Rolle>,
)

data class Rolle(
    val orgnummer: String,
    val rolletype: String,
)
