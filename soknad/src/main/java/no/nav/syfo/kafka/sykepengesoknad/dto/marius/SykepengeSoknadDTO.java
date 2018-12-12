package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import no.nav.syfo.kafka.sykepengesoknad.dto.SoknadSporsmalDTO;

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
    private List<Periode> egenmeldingListe;
    private List<Periode> papirSykmeldingListe;
    private List<Periode> annenSykmeldingListe;
    private List<Inntektskilde> andreInntektListe;
    private SoknadSporsmalDTO soknadsdto;

    public String getUtdanning(){
        for(Fravaer fravaer:fravaerListe) {
            if (fravaer.getType().contains("UTDANNING"))
                return fravaer.getType();
        }
        return null;
    }

}
