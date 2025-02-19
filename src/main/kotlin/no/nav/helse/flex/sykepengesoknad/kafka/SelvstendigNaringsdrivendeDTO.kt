package no.nav.helse.flex.sykepengesoknad.kafka

import java.math.BigInteger

data class SelvstendigNaringsdrivendeDTO(
    val roller: List<RolleDTO>,
    val sykepengegrunnlagNaeringsdrivende: SykepengegrunnlagNaeringsdrivendeDTO? = null,
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
    val pensjonsgivendeInntektAvLoennsinntekt: Int,
    val pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel: Int,
    val pensjonsgivendeInntektAvNaeringsinntekt: Int,
    val pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage: Int,
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
