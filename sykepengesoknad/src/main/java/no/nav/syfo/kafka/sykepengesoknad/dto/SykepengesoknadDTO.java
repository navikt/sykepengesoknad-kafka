package no.nav.syfo.kafka.sykepengesoknad.dto;

import lombok.Builder;
import lombok.Value;
import no.nav.syfo.kafka.interfaces.Soknad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Value
@Builder(toBuilder = true)
public class SykepengesoknadDTO implements Soknad {
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
    ArbeidsgiverForskuttererDTO arbeidsgiverForskutterer;
    LocalDate fom;
    LocalDate tom;
    LocalDate startSyketilfelle;
    LocalDate arbeidGjenopptatt;
    LocalDateTime sykmeldingSkrevet;
    LocalDateTime opprettet;
    LocalDateTime sendtNav;
    LocalDateTime sendtArbeidsgiver;
    List<PeriodeDTO> egenmeldinger;
    List<PeriodeDTO> papirsykmeldinger;
    List<FravarDTO> fravar;
    List<InntektskildeDTO> andreInntektskilder;
    List<SoknadsperiodeDTO> soknadsperioder;
    List<SporsmalDTO> sporsmal;
    AvsendertypeDTO avsendertype;
    boolean ettersending;
    MottakerDTO mottaker;
}
