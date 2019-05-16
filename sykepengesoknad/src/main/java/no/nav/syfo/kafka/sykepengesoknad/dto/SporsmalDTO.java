package no.nav.syfo.kafka.sykepengesoknad.dto;

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
    String min;
    String max;
    SvartypeDTO svartype;
    VisningskriteriumDTO kriteriumForVisningAvUndersporsmal;
    List<SvarDTO> svar;
    List<SporsmalDTO> undersporsmal;
}
