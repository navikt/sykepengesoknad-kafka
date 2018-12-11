package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder(toBuilder = true)
@Getter
public class KorrigertArbeidstid {
    LocalDate fom;
    LocalDate tom;
    Double avtalt_timer;
    Double faktisk_grad;
    Double faktisk_timer;

}