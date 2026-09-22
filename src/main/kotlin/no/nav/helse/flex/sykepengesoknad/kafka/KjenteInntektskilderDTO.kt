package no.nav.helse.flex.sykepengesoknad.kafka

enum class KildeDTO {
    INNTEKTSKOMPONENTEN,
    AAAREG,
    SYKMELDING,
}

data class KjenteInntektskilderDTO(
    val navn: String,
    val kilde: KildeDTO,
    val orgnummer: String,
)
