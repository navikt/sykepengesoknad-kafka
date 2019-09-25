package no.nav.syfo.kafka.sykepengesoknadarbeidsledig.dto

data class SporsmalDTO(
        val id: String,
        val tag: String,
        val sporsmalstekst: String? = null,
        val undertekst: String? = null,
        val min: String? = null,
        val max: String? = null,
        val svartype: SvartypeDTO? = null,
        val kriteriumForVisningAvUndersporsmal: VisningskriteriumDTO? = null,
        val svar: List<SvarDTO>,
        val undersporsmal: List<SporsmalDTO>
)
