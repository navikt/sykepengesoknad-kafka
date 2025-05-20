package no.nav.helse.flex.sykepengesoknad.arbeidsgiverwhitelist

import no.nav.helse.flex.sykepengesoknad.kafka.NaringsdrivendeInntektDTO
import no.nav.helse.flex.sykepengesoknad.kafka.NaringsdrivendeInntektsAarDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SummertPensjonsgivendeInntektDTO
import org.amshove.kluent.`should be equal to`
import org.junit.jupiter.api.Test

class NaringsdrivendeInntektDTOTest {
    @Test
    fun `Verifiser at NaringsdrivendeInntektDTO har riktig innhold`() {
        val naringsdrivendeInntekt =
            NaringsdrivendeInntektDTO(
                norskPersonidentifikator = "11111111111",
                inntekt =
                    listOf(
                        NaringsdrivendeInntektsAarDTO(
                            inntektsaar = "20204",
                            pensjonsgivendeInntekt =
                                SummertPensjonsgivendeInntektDTO(
                                    pensjonsgivendeInntektAvLoennsinntekt = 100000,
                                    pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel = 50000,
                                    pensjonsgivendeInntektAvNaeringsinntekt = 300000,
                                    pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage = 12000,
                                ),
                        ),
                        NaringsdrivendeInntektsAarDTO(
                            inntektsaar = "2025",
                            pensjonsgivendeInntekt =
                                SummertPensjonsgivendeInntektDTO(
                                    pensjonsgivendeInntektAvLoennsinntekt = 800000,
                                    pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel = 60000,
                                    pensjonsgivendeInntektAvNaeringsinntekt = 400000,
                                    pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage = 9000,
                                ),
                        ),
                    ),
            )

        naringsdrivendeInntekt.norskPersonidentifikator `should be equal to` "11111111111"
        naringsdrivendeInntekt.inntekt.size `should be equal to` 2

        naringsdrivendeInntekt.inntekt.first().also {
            it.inntektsaar `should be equal to` "20204"
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 100000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel `should be equal to` 50000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt `should be equal to` 300000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage `should be equal to` 12000
        }

        naringsdrivendeInntekt.inntekt.last().also {
            it.inntektsaar `should be equal to` "2025"
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntekt `should be equal to` 800000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvLoennsinntektBarePensjonsdel `should be equal to` 60000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntekt `should be equal to` 400000
            it.pensjonsgivendeInntekt.pensjonsgivendeInntektAvNaeringsinntektFraFiskeFangstEllerFamiliebarnehage `should be equal to` 9000
        }
    }
}
