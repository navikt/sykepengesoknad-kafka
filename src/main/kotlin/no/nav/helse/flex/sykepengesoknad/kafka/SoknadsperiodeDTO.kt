package no.nav.helse.flex.sykepengesoknad.kafka

import java.time.LocalDate

data class SoknadsperiodeDTO(
    /**
     * Første dag i sykmeldingsperioden.
     */
    val fom: LocalDate? = null,
    /**
     * Siste dag i sykmeldingsperioden.
     */
    val tom: LocalDate? = null,
    /**
     * Sykmeldingsgrad fra sykmeldingen. Sammenfaller med at sykmeldingstype er
     * SykmeldingstypeDTO.GRADERT hvis graderingen er mindre enn 100 %.
     */
    val sykmeldingsgrad: Int? = null,
    /**
     * Hvor mange prosent av avtalte timer per uke det ble jobbet.
     */
    val faktiskGrad: Int? = null,
    /**
     * Normal (avtalte) antall arbeidstimer per uke.
     */
    val avtaltTimer: Double? = null,
    /**
     * Timer faktisk jobbet i sykmeldingsperioden.
     */
    val faktiskTimer: Double? = null,
    val sykmeldingstype: SykmeldingstypeDTO? = null,
    // Fra gammel SoknadDTO. Bør konsolideres med sykmeldingsgrad.
    val grad: Int? = null,
)
