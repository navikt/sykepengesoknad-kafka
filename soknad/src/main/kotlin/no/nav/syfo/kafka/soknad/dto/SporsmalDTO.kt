package no.nav.syfo.kafka.soknad.dto

data class SporsmalDTO(
        val id: String? = null,
        val tag: String? = null,
        val sporsmalstekst: String? = null,
        val undertekst: String? = null,
        val svartype: String? = null,
        val min: String? = null,
        val max: String? = null,
        val kriterieForVisningAvUndersporsmal: String? = null,
        val svar: List<SvarDTO>? = null,
        val undersporsmal: List<SporsmalDTO>? = null
)
