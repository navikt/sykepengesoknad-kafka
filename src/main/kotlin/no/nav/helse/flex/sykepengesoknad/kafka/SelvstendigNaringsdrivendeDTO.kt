package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate

data class SelvstendigNaringsdrivendeDTO(
    val roller: List<RolleDTO>,
    val inntekt: InntektDTO? = null,
    val hovedSporsmalSvar: Map<String, Boolean> = emptyMap(),
    val brukerHarOppgittForsikring: Boolean,
)

data class InntektDTO(
    val norskPersonidentifikator: String,
    val inntektsAar: List<InntektsAarDTO>,
)

data class InntektsAarDTO(
    val aar: String,
    val pensjonsgivendeInntekt: PensjonsgivendeInntektDTO?,
    val erFerdigLignet: Boolean = pensjonsgivendeInntekt != null,
    val pensjonsgivendeInntektPerSkatteordning: List<PensjonsgivendeInntektPerSkatteordningDTO> = emptyList(),
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

/**
 * Pensjonsgivende inntekt for én skatteordning, uten summering.
 */
data class PensjonsgivendeInntektPerSkatteordningDTO(
    val skatteordning: SkatteordningDTO,
    val datoForFastsetting: LocalDate,
    val pensjonsgivendeInntektAvLoennsinntekt: Int = 0,
    val pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel: Int = 0,
    val pensjonsgivendeInntektAvNaeringsinntekt: Int = 0,
    val pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage: Int = 0,
)

enum class SkatteordningDTO {
    FASTLAND,
    SVALBARD,
    KILDESKATT_PAA_LOENN,
}

data class RolleDTO(
    val orgnummer: String,
    val rolletype: String,
)
