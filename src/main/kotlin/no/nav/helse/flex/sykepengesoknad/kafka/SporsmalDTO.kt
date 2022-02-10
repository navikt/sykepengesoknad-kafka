package no.nav.helse.flex.sykepengesoknad.kafka

data class SporsmalDTO(
    val id: String? = null,
    val tag: String? = null,
    val sporsmalstekst: String? = null,
    val undertekst: String? = null,
    val min: String? = null,
    val max: String? = null,
    val svartype: no.nav.helse.flex.sykepengesoknad.kafka.SvartypeDTO? = null,
    val kriterieForVisningAvUndersporsmal: no.nav.helse.flex.sykepengesoknad.kafka.VisningskriteriumDTO? = null,
    val svar: List<no.nav.helse.flex.sykepengesoknad.kafka.SvarDTO>? = null,
    val undersporsmal: List<no.nav.helse.flex.sykepengesoknad.kafka.SporsmalDTO>? = null
)
