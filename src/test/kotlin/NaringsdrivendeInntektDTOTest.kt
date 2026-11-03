package no.nav.helse.flex.sykepengesoknad.arbeidsgiverwhitelist

import io.kotest.matchers.shouldBe
import no.nav.helse.flex.sykepengesoknad.kafka.InntektDTO
import no.nav.helse.flex.sykepengesoknad.kafka.InntektsAarDTO
import no.nav.helse.flex.sykepengesoknad.kafka.PensjonsgivendeInntektDTO
import no.nav.helse.flex.sykepengesoknad.kafka.PensjonsgivendeInntektPerSkatteordningDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SkatteordningDTO
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

        naringsdrivendeInntekt.norskPersonidentifikator shouldBe "11111111111"
        naringsdrivendeInntekt.inntektsAar.size shouldBe 2

        naringsdrivendeInntekt.inntektsAar.first().also {
            it.aar shouldBe "2024"
            it.erFerdigLignet shouldBe true
            it.pensjonsgivendeInntekt!!.pensjonsgivendeInntektAvLoennsinntekt shouldBe 100000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel shouldBe 50000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt shouldBe 300000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage shouldBe 12000
        }

        naringsdrivendeInntekt.inntektsAar.last().also {
            it.aar shouldBe "2025"
            it.erFerdigLignet shouldBe true
            it.pensjonsgivendeInntekt!!.pensjonsgivendeInntektAvLoennsinntekt shouldBe 800000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel shouldBe 60000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt shouldBe 400000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage shouldBe 9000
        }
    }

    @Test
    fun `InntektsAarDTO som mangler pensjonsgivendeInntekt skal tolkes som ikke ferdig lignet`() {
        val inntektsAar =
            InntektsAarDTO(
                aar = "2025",
                pensjonsgivendeInntekt = null,
            )

        inntektsAar.erFerdigLignet shouldBe false
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

        inntektsAar.erFerdigLignet shouldBe true
        inntektsAar.pensjonsgivendeInntektPerSkatteordning.size shouldBe 2

        inntektsAar.pensjonsgivendeInntektPerSkatteordning[0].also {
            it.skatteordning shouldBe SkatteordningDTO.FASTLAND
            it.datoForFastsetting shouldBe LocalDate.of(2024, 5, 15)
            it.pensjonsgivendeInntektAvLoennsinntekt shouldBe 100000
        }

        inntektsAar.pensjonsgivendeInntektPerSkatteordning[1].also {
            it.skatteordning shouldBe SkatteordningDTO.SVALBARD
            it.datoForFastsetting shouldBe LocalDate.of(2024, 5, 16)
            it.pensjonsgivendeInntektAvLoennsinntekt shouldBe 200000
        }
    }

    @Test
    fun `InntektsAarDTO uten pensjonsgivendeInntektPerSkatteordning har tom liste som default`() {
        val inntektsAar =
            InntektsAarDTO(
                aar = "2024",
                pensjonsgivendeInntekt = null,
            )

        inntektsAar.pensjonsgivendeInntektPerSkatteordning.isEmpty() shouldBe true
    }
}
