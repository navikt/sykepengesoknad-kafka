package no.nav.helse.flex.sykepengesoknad.arbeidsgiverwhitelist

import no.nav.helse.flex.sykepengesoknad.kafka.SporsmalDTO
import no.nav.helse.flex.sykepengesoknad.kafka.SykepengesoknadDTO

private val whitelistetHovedsporsmal =
    listOf(
        // Vanlige spørsmål
        "ANSVARSERKLARING",
        "TILBAKE_I_ARBEID",
        "FERIE_V2",
        "PERMISJON_V2",
        "UTLAND_V2",
        "JOBBET_DU_100_PROSENT",
        "JOBBET_DU_GRADERT",
        "ARBEID_UNDERVEIS_100_PROSENT",
        // behandlingsdager
        "FRAVER_FOR_BEHANDLING",
        "ENKELTSTAENDE_BEHANDLINGSDAGER",
        // gradert reisetilskudd
        "BRUKTE_REISETILSKUDDET",
        "TRANSPORT_TIL_DAGLIG",
        "REISE_MED_BIL",
        "KVITTERINGER",
        "UTBETALING",
        // sisteside
        "VAER_KLAR_OVER_AT",
        "BEKREFT_OPPLYSNINGER",
        "TIL_SLUTT",
        // deprecated spørsmål vi likevel støtter
        "UTDANNING",
        "FERIE_PERMISJON_UTLAND",
        "EGENMELDINGER",
        "PERMITTERT_NAA",
        "PERMITTERT_PERIODE",
        "FRAVAR_FOR_SYKMELDINGEN",
        "UTLAND",
    )

private fun String.fjernTagIndex(): String {
    val regex = "_\\d+$".toRegex()
    return regex.replace(this, "")
}

fun SporsmalDTO.erWhitelistetForArbeidsgiver(): Boolean {
    if (this.tag == null) {
        return false
    }
    return this.tag.fjernTagIndex() in whitelistetHovedsporsmal
}

fun SykepengesoknadDTO.whitelistetForArbeidsgiver(): SykepengesoknadDTO =
    this.copy(sporsmal = this.sporsmal?.filter { it.erWhitelistetForArbeidsgiver() })
