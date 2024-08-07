package no.nav.helse.flex.sykepengesoknad.arbeidsgiverwhitelist

import no.nav.helse.flex.sykepengesoknad.kafka.SoknadsstatusDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SoknadstypeDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SporsmalDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SykepengesoknadDTO
import org.amshove.kluent.`should be false`
import org.amshove.kluent.`should be true`
import org.amshove.kluent.shouldHaveSize
import org.junit.jupiter.api.Test

class ArbeidsgiverWhitelistKtTest {
    @Test
    fun `tester at ting som ikke er whitelistet returnerer false`() {
        listOf(
            "YRKESSKADE",
            "YRKESSKADE_V2",
            "ANDRE_INNTEKTSKILDER",
            "ANDRE_INNTEKTSKILDER_V2",
            "ARBEID_UTENFOR_NORGE",
            "UTENLANDSK_SYKMELDING_BOSTED",
            "UTENLANDSK_SYKMELDING_TRYGD_UTENFOR_NORGE",
            "UTENLANDSK_SYKMELDING_LONNET_ARBEID_UTENFOR_NORGE",
            "MEDLEMSKAP_OPPHOLDSTILLATELSE",
            "MEDLEMSKAP_UTFORT_ARBEID_UTENFOR_NORGE",
            "MEDLEMSKAP_OPPHOLD_UTENFOR_EOS",
            "MEDLEMSKAP_OPPHOLD_UTENFOR_NORGE",
        ).forEach { tag ->
            sporsmal(tag).erWhitelistetForArbeidsgiver().`should be false`()
        }
    }

    @Test
    fun `tester at ting som er whitelistet returnerer true`() {
        listOf(
            // Vanlige spørsmål
            "ANSVARSERKLARING",
            "TILBAKE_I_ARBEID",
            "FERIE_V2",
            "PERMISJON_V2",
            "UTLAND_V2",
            "JOBBET_DU_100_PROSENT",
            "JOBBET_DU_100_PROSENT_0",
            "JOBBET_DU_100_PROSENT_1",
            "JOBBET_DU_100_PROSENT_2",
            "JOBBET_DU_100_PROSENT_3",
            "JOBBET_DU_100_PROSENT_10",
            "JOBBET_DU_GRADERT",
            "JOBBET_DU_GRADERT_0",
            "JOBBET_DU_GRADERT_1",
            "JOBBET_DU_GRADERT_9",
            "JOBBET_DU_GRADERT_99",
            "ARBEID_UNDERVEIS_100_PROSENT",
            "ARBEID_UNDERVEIS_100_PROSENT_0",
            "ARBEID_UNDERVEIS_100_PROSENT_1",
            "ARBEID_UNDERVEIS_100_PROSENT_122",
            // behandlingsdager
            "FRAVER_FOR_BEHANDLING",
            "ENKELTSTAENDE_BEHANDLINGSDAGER",
            // gradert reisetilskudd
            "BRUKTE_REISETILSKUDDET",
            "TRANSPORT_TIL_DAGLIG",
            "REISE_MED_BIL",
            "KVITTERINGER",
            "UTBETALING",
            // deprecated spørsmål vi likevel støtter
            "UTDANNING",
            "FERIE_PERMISJON_UTLAND",
            "EGENMELDINGER",
            "PERMITTERT_NAA",
            "PERMITTERT_PERIODE",
            "FRAVAR_FOR_SYKMELDINGEN",
            "UTLAND",
            "OPPHOLD_UTENFOR_EOS",
        ).forEach { tag ->
            sporsmal(tag).erWhitelistetForArbeidsgiver().`should be true`()
        }
    }

    @Test
    fun `tester prossesering av whitelist på søknad `() {
        val soknad =
            SykepengesoknadDTO(
                fnr = "123",
                sporsmal = listOf(sporsmal("ANDRE_INNTEKTSKILDER_V2"), sporsmal("ARBEID_UNDERVEIS_100_PROSENT_0")),
                id = "id",
                status = SoknadsstatusDTO.SENDT,
                type = SoknadstypeDTO.ARBEIDSTAKERE,
            )
        soknad.sporsmal!!.shouldHaveSize(2)
        soknad.whitelistetForArbeidsgiver().sporsmal!!.shouldHaveSize(1)
    }

    fun sporsmal(tag: String): SporsmalDTO {
        return SporsmalDTO(tag = tag)
    }
}
