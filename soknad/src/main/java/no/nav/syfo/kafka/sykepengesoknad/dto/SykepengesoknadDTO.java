package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SykepengesoknadDTO {
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
