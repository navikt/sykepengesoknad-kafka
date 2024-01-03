package no.nav.helse.flex.sykepengesoknad.kafka

data class SporsmalDTO(
    val id: String? = null,
    val tag: String? = null,
    val sporsmalstekst: String? = null,
    val undertekst: String? = null,
    val min: String? = null,
    val max: String? = null,
    val svartype: SvartypeDTO? = null,
    val kriterieForVisningAvUndersporsmal: VisningskriteriumDTO? = null,
    val svar: List<SvarDTO>? = null,
    val undersporsmal: List<SporsmalDTO>? = null,
)
