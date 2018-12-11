package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Inntektskilde {
    String navn;
    boolean sykemeldt;
}
