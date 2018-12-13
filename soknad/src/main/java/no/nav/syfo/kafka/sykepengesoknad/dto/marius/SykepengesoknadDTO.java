package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import no.nav.syfo.kafka.sykepengesoknad.dto.SoknadPeriodeDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.SporsmalDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SykepengesoknadDTO {
    String id;
    String status;
    String soknadstype;
    String aktorId;
    String orgnummer;
    String sykmeldingId;
    String arbeidsgiver;
    String arbeidssituasjon;
    String korrigerer;
    String korrigertAv;
    boolean soktUtenlandsopphold;
    LocalDate fom;
    LocalDate tom;
    LocalDate startSykeforlop;
    LocalDate sykmeldingUtskrevet;
    LocalDate arbeidGjenopptatt;
    LocalDateTime opprettet;
    LocalDateTime sendtNav;
    LocalDateTime sendtArbeidsgiver;
    List<Periode> egenmeldinger;
    List<Periode> papirsykmeldinger;
    List<Fravar> fravaer;
    List<Inntektskilde> andreInntektskilder;
    List<SoknadPeriodeDTO> soknadPerioder;
    List<SporsmalDTO> sporsmal;
}
