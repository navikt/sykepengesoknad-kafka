package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import no.nav.syfo.kafka.sykepengesoknad.dto.SoknadSporsmalDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
@Builder(toBuilder = true)
@Getter
public class SykepengeSoknadDTO {
    private String soknadstype;
    private String aktorId;
    private String orgnummer;
    private String sykmeldingId;
    private List<KorrigertArbeidstid> korrigertArbeidstidListe;
    private List<Fravaer> fravaerListe;
    private boolean utenlands;
    private String utdanningsgrad;
    private List<Periode> egenmeldingListe;
    private List<Periode> papirsykmeldingListe;
    private List<Periode> annensykmeldingListe;
    private List<Inntektskilde> andreinntektListe;
    private SoknadSporsmalDTO soknadsdto;

public List<KorrigertArbeidstid> getKorrigertArbeidstid(){
    return korrigertArbeidstidListe==null ?
            new ArrayList<>() : korrigertArbeidstidListe;
}

}
