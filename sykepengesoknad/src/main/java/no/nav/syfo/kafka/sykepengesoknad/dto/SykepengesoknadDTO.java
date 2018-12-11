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
    SoknadstypeDTO type;
    SoknadsstatusDTO status;
    String aktorId;
    String sykmeldingId;
    ArbeidsgiverDTO arbeidsgiver;
    ArbeidssituasjonDTO arbeidssituasjon;
    String korrigerer;
    String korrigertAv;
    Boolean soktUtenlandsopphold;
    Boolean arbeidsgiverForskutterer;
    LocalDate fom;
    LocalDate tom;
    LocalDate startSykeforlop;
    LocalDate arbeidGjenopptatt;
    LocalDateTime sykmeldingSkrevet;
    LocalDateTime opprettet;
    LocalDateTime sendtNav;
    LocalDateTime sendtArbeidsgiver;
    List<PeriodeDTO> egenmeldinger;
    List<PeriodeDTO> papirsykmeldinger;
    List<FravarDTO> fravar;
    List<InntektskildeDTO> andreInntektskilder;
    List<SoknadsperiodeDTO> soknadPerioder;
    List<SporsmalDTO> sporsmal;
}