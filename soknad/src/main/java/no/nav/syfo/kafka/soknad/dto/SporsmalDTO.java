package no.nav.syfo.kafka.soknad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder(toBuilder = true)
@JsonIgnoreProperties(ignoreUnknown = true)
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
