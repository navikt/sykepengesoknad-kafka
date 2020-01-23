package no.nav.syfo.kafka.sykepengesoknadbehandlingsdager.dto

import no.nav.syfo.kafka.felles.SkjultVerdi
import no.nav.syfo.kafka.felles.SoknadFellesDTO
import no.nav.syfo.kafka.felles.SoknadsstatusDTO
import no.nav.syfo.kafka.felles.SykepengesoknadFellesDTO
import org.junit.jupiter.api.Test
import java.time.LocalDate
import org.assertj.core.api.Assertions.assertThat


class SykepengesoknadBehandlingsdagerDTOTest {

    @Test
    fun testAtFnrMaskeres() {

        val soknad = SykepengesoknadBehandlingsdagerDTO(
                soknadFelles = SoknadFellesDTO(
                        id = "id",
                        aktorId = "aktørr",
                        fodselsnummer = SkjultVerdi("maskeresBort"),
                        status = SoknadsstatusDTO.KORRIGERT,
                        opprettet = LocalDate.of(2015, 1, 1).atStartOfDay(),
                        sporsmal = emptyList()
                ),
                sykepengesoknadFelles = SykepengesoknadFellesDTO(
                        sykmeldingId = "hei",
                        fom = LocalDate.of(2015, 1, 1),
                        tom = LocalDate.of(2015, 1, 1),
                        startSyketilfelle = LocalDate.of(2015, 1, 1),
                        sykmeldingSkrevet = LocalDate.of(2015, 1, 1).atStartOfDay(),
                        soknadsperioder = emptyList()
                ),
                behandlingsdager = emptyList()
        )

        assertThat(soknad.toString()).isEqualTo("SykepengesoknadBehandlingsdagerDTO(soknadFelles=SoknadFellesDTO(id=id, aktorId=aktørr, fodselsnummer=███████████, status=KORRIGERT, sendtNav=null, korrigerer=null, korrigertAv=null, opprettet=2015-01-01T00:00, avsendertype=null, sporsmal=[], arbeidsgiver=null, arbeidsgiverForskutterer=null, ettersending=false, mottaker=null, sendtArbeidsgiver=null, arbeidssituasjon=null), sykepengesoknadFelles=SykepengesoknadFellesDTO(sykmeldingId=hei, fom=2015-01-01, tom=2015-01-01, startSyketilfelle=2015-01-01, sykmeldingSkrevet=2015-01-01T00:00, soknadsperioder=[]), egenmeldinger=null, behandlingsdager=[], papirsykmeldinger=null, andreInntektskilder=null)")
    }
}

