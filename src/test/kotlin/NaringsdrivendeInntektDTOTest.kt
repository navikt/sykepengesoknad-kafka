package no.nav.helse.flex.sykepengesoknad.arbeidsgiverwhitelist

import no.nav.helse.flex.sykepengesoknad.kafka.InntektDTO
import no.nav.helse.flex.sykepengesoknad.kafka.InntektsAarDTO
import no.nav.helse.flex.sykepengesoknad.kafka.PensjonsgivendeInntektDTO
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

class NaringsdrivendeInntektDTOTest {
    @Test
    fun `Verifiser at NaringsdrivendeInntektDTO har riktig innhold`() {
        val naringsdrivendeInntekt =
            InntektDTO(
                norskPersonidentifikator = "11111111111",
                inntektsAar =
                    listOf(
                        InntektsAarDTO(
                            aar = "2024",
                            pensjonsgivendeInntekt =
                                PensjonsgivendeInntektDTO(
                                    pensjonsgivendeInntektAvLoennsinntekt = 100000,
                                    pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel = 50000,
                                    pensjonsgivendeInntektAvNaeringsinntekt = 300000,
                                    pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage = 12000,
                                ),
                        ),
                        InntektsAarDTO(
                            aar = "2025",
                            pensjonsgivendeInntekt =
                                PensjonsgivendeInntektDTO(
                                    pensjonsgivendeInntektAvLoennsinntekt = 800000,
                                    pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel = 60000,
                                    pensjonsgivendeInntektAvNaeringsinntekt = 400000,
                                    pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage = 9000,
                                ),
                        ),
                    ),
            )

        naringsdrivendeInntekt.norskPersonidentifikator `should be equal to` "11111111111"
        naringsdrivendeInntekt.inntektsAar.size `should be equal to` 2

        naringsdrivendeInntekt.inntektsAar.first().also {
            it.aar `should be equal to` "2024"
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 100000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel `should be equal to` 50000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt `should be equal to` 300000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage `should be equal to` 12000
        }

        naringsdrivendeInntekt.inntektsAar.last().also {
            it.aar `should be equal to` "2025"
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 800000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel `should be equal to` 60000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt `should be equal to` 400000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage `should be equal to` 9000
        }
    }
}
