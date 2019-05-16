package no.nav.syfo.kafka.soknad.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
public class SporsmalDTO {
    String id;
    String tag;
    String sporsmalstekst;
    String undertekst;
    String svartype;
    String min;
    String max;
    String kriterieForVisningAvUndersporsmal;
    List<SvarDTO> svar;
    List<SporsmalDTO> undersporsmal;
}
