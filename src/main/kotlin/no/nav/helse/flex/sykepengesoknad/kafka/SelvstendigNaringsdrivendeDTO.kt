package no.nav.helse.flex.sykepengesoknad.kafka

import java.math.BigInteger

data class SelvstendigNaringsdrivendeDTO(
    val roller: List<RolleDTO>,
    @Deprecated(
        message = "Fjernes når alle konsumenter har tatt i bruk NaringsdrivendeInntektDTO.",
        replaceWith = ReplaceWith("naringsdrivendeInntekt"),
    )
    val sykepengegrunnlagNaeringsdrivende: SykepengegrunnlagNaeringsdrivendeDTO? = null,
    val naringsdrivendeInntekt: NaringsdrivendeInntektDTO? = null,
)

data class NaringsdrivendeInntektDTO(
    val norskPersonidentifikator: String,
    val inntekt: List<NaringsdrivendeInntektsAarDTO>,
)

data class NaringsdrivendeInntektsAarDTO(
    val inntektsaar: String,
    val pensjonsgivendeInntekt: SummertPensjonsgivendeInntektDTO,
)

// Inneholder summert verdier for skatteordningene SVALBARD og FASTLAND.
data class SummertPensjonsgivendeInntektDTO(
    val pensjonsgivendeInntektAvLoennsinntekt: Int? = 0,
    val pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel: Int? = 0,
    val pensjonsgivendeInntektAvNaeringsinntekt: Int? = 0,
    val pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage: Int? = 0,
)

data class AarVerdiDTO(
    val aar: String,
    val verdi: BigInteger,
)

data class BeregnetDTO(
    val snitt: BigInteger,
    val p25: BigInteger,
    val m25: BigInteger,
)

data class SykepengegrunnlagNaeringsdrivendeDTO(
    val gjennomsnittPerAar: List<AarVerdiDTO>,
    val grunnbeloepPerAar: List<AarVerdiDTO>,
    val grunnbeloepPaaSykmeldingstidspunkt: Int,
    val beregnetSnittOgEndring25: BeregnetDTO,
    val inntekter: List<HentPensjonsgivendeInntektResponseDTO>,
)

data class HentPensjonsgivendeInntektResponseDTO(
    val norskPersonidentifikator: String,
    val inntektsaar: String,
    val pensjonsgivendeInntekt: List<PensjonsgivendeInntektDTO>,
)

data class PensjonsgivendeInntektDTO(
    val datoForFastsetting: String,
    val skatteordning: SkatteordningDTO,
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
