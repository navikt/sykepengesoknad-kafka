package no.nav.helse.flex.sykepengesoknad.kafka

data class SelvstendigNaringsdrivendeDTO(
    val roller: List<RolleDTO>,
    val naringsdrivendeInntekt: NaringsdrivendeInntektDTO? = null,
    val naringsdrivendeVenteperiode: PeriodeDTO? = null,
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

data class RolleDTO(
    val orgnummer: String,
    val rolletype: String,
)
