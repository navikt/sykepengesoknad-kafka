package no.nav.syfo.kafka.sykepengesoknad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SykepengesoknadDTO {
    String id;
    SoknadType type;
    SoknadStatus status;
    String aktorId;
    String sykmeldingId;
    ArbeidsgiverDTO arbeidsgiver;
    Arbeidssitasjon arbeidssituasjon;
    String korrigerer;
    String korrigertAv;
    Boolean soktUtenlandsopphold;
    LocalDate fom;
    LocalDate tom;
    LocalDate startSykeforlop;
    LocalDate sykmeldingUtskrevet;
    LocalDate arbeidGjenopptatt;
    LocalDateTime opprettet;
    LocalDateTime sendtNav;
    LocalDateTime sendtArbeidsgiver;
    List<PeriodeDTO> egenmeldinger;
    List<PeriodeDTO> papirsykmeldinger;
    List<FravarDTO> fravar;
    List<InntektskildeDTO> andreInntektskilder;
    List<SoknadPeriodeDTO> soknadPerioder;
    List<SporsmalDTO> sporsmal;
}
