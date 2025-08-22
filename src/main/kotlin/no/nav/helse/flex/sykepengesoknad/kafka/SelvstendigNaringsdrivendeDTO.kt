package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate

data class SelvstendigNaringsdrivendeDTO(
    val roller: List<RolleDTO>,
    val inntekt: InntektDTO? = null,
    val ventetid: VentetidDTO? = null,
)

data class VentetidDTO(
    val fom: LocalDate?,
    val tom: LocalDate?,
)

data class InntektDTO(
    val norskPersonidentifikator: String,
    val inntektsAar: List<InntektsAarDTO>,
)

data class InntektsAarDTO(
    val aar: String,
    val pensjonsgivendeInntekt: PensjonsgivendeInntektDTO,
)

/**
 * Inneholder summert verdier for skatteordningene SVALBARD og FASTLAND.
 */
data class PensjonsgivendeInntektDTO(
    val pensjonsgivendeInntektAvLoennsinntekt: Int? = 0,
    val pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel: Int? = 0,
    val pensjonsgivendeInntektAvNaeringsinntekt: Int? = 0,
    val pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage: Int? = 0,
)

data class RolleDTO(
    val orgnummer: String,
    val rolletype: String,
)
