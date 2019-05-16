package no.nav.syfo.kafka.soknad.dto;

import lombok.Builder;
import lombok.Value;
import no.nav.syfo.kafka.interfaces.Soknad;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class SoknadDTO implements Soknad {
    String id;
    String aktorId;
    String sykmeldingId;
    String soknadstype;
    String status;
    LocalDate fom;
    LocalDate tom;
    LocalDate opprettetDato;
    LocalDate innsendtDato;
    LocalDate startSykeforlop;
    LocalDate sykmeldingUtskrevet;
    String arbeidsgiver;
    String arbeidssituasjon;
    String korrigerer;
    String korrigertAv;
    List<SoknadPeriodeDTO> soknadPerioder;
    List<SporsmalDTO> sporsmal;
}
