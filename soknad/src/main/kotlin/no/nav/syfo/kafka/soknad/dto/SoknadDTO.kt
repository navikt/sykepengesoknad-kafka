package no.nav.syfo.kafka.soknad.dto

import no.nav.syfo.kafka.felles.AvsendertypeDTO
import no.nav.syfo.kafka.felles.SkjultVerdi
import no.nav.syfo.kafka.felles.SoknadsperiodeDTO
import no.nav.syfo.kafka.felles.SporsmalDTO
import no.nav.syfo.kafka.interfaces.Soknad
import java.time.LocalDate

data class SoknadDTO(
        val id: String? = null,
        val aktorId: String? = null,
        val sykmeldingId: String? = null,
        val soknadstype: String? = null,
        val status: String? = null,
        val fom: LocalDate? = null,
        val tom: LocalDate? = null,
        val opprettetDato: LocalDate? = null,
        val innsendtDato: LocalDate? = null,
        val startSykeforlop: LocalDate? = null,
        val sykmeldingUtskrevet: LocalDate? = null,
        val arbeidsgiver: String? = null,
        val arbeidssituasjon: String? = null,
        val korrigerer: String? = null,
        val korrigertAv: String? = null,
        val soknadPerioder: List<SoknadsperiodeDTO>? = null,
        val sporsmal: List<SporsmalDTO>? = null,
        val avsendertype: AvsendertypeDTO? = null,
        val fodselsnummer: SkjultVerdi<String>? = null,
        val egenmeldtSykmelding: Boolean? = null
) : Soknad
