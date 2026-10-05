package no.nav.helse.flex.sykepengesoknad.arbeidsgiverwhitelist

import no.nav.helse.flex.sykepengesoknad.kafka.InntektDTO
import no.nav.helse.flex.sykepengesoknad.kafka.InntektsAarDTO
import no.nav.helse.flex.sykepengesoknad.kafka.PensjonsgivendeInntektDTO
import no.nav.helse.flex.sykepengesoknad.kafka.PensjonsgivendeInntektPerSkatteordningDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SkatteordningDTO
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test
import java.time.LocalDate

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
            it.erFerdigLignet `should be equal to` true
            it.pensjonsgivendeInntekt!!.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 100000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel `should be equal to` 50000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt `should be equal to` 300000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage `should be equal to` 12000
        }

        naringsdrivendeInntekt.inntektsAar.last().also {
            it.aar `should be equal to` "2025"
            it.erFerdigLignet `should be equal to` true
            it.pensjonsgivendeInntekt!!.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 800000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel `should be equal to` 60000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt `should be equal to` 400000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage `should be equal to` 9000
        }
    }

    @Test
    fun `InntektsAarDTO som mangler pensjonsgivendeInntekt skal tolkes som ikke ferdig lignet`() {
        val inntektsAar =
            InntektsAarDTO(
                aar = "2025",
                pensjonsgivendeInntekt = null,
            )

        inntektsAar.erFerdigLignet `should be equal to` false
    }

    @Test
    fun `InntektsAarDTO med FASTLAND og SVALBARD beholder hver skatteordning`() {
        val inntektsAar =
            InntektsAarDTO(
                aar = "2024",
                pensjonsgivendeInntekt =
                    PensjonsgivendeInntektDTO(
                        pensjonsgivendeInntektAvLoennsinntekt = 300000,
                    ),
                pensjonsgivendeInntektPerSkatteordning =
                    listOf(
                        PensjonsgivendeInntektPerSkatteordningDTO(
                            skatteordning = SkatteordningDTO.FASTLAND,
                            datoForFastsetting = LocalDate.of(2024, 5, 15),
                            pensjonsgivendeInntektAvLoennsinntekt = 100000,
                        ),
                        PensjonsgivendeInntektPerSkatteordningDTO(
                            skatteordning = SkatteordningDTO.SVALBARD,
                            datoForFastsetting = LocalDate.of(2024, 5, 16),
                            pensjonsgivendeInntektAvLoennsinntekt = 200000,
                        ),
                    ),
            )

        inntektsAar.erFerdigLignet `should be equal to` true
        inntektsAar.pensjonsgivendeInntektPerSkatteordning.size `should be equal to` 2

        inntektsAar.pensjonsgivendeInntektPerSkatteordning[0].also {
            it.skatteordning `should be equal to` SkatteordningDTO.FASTLAND
            it.datoForFastsetting `should be equal to` LocalDate.of(2024, 5, 15)
            it.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 100000
        }

        inntektsAar.pensjonsgivendeInntektPerSkatteordning[1].also {
            it.skatteordning `should be equal to` SkatteordningDTO.SVALBARD
            it.datoForFastsetting `should be equal to` LocalDate.of(2024, 5, 16)
            it.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 200000
        }
    }

    @Test
    fun `InntektsAarDTO uten pensjonsgivendeInntektPerSkatteordning har tom liste som default`() {
        val inntektsAar =
            InntektsAarDTO(
                aar = "2024",
                pensjonsgivendeInntekt = null,
            )

        inntektsAar.pensjonsgivendeInntektPerSkatteordning.isEmpty() `should be equal to` true
    }
}
