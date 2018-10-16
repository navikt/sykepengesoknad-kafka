package no.nav.syfo.kafka.sykepengesoknad.serializer;

import no.nav.syfo.kafka.sykepengesoknad.dto.SporsmalDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.SvarDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.SykepengesoknadDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class SykepengesoknadSerializerTest {

    @Test
    public void test() {
        String s = "{\"id\":\"id\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettetDato\":\"2018-10-15\"," +
                "\"innsendtDato\":\"2018-10-15\"," +
                "\"sporsmal\":[{\"id\":\"id\"," +
                "\"tag\":\"tag\"," +
                "\"sporsmalstekst\":\"sporsmalstekst\"," +
                "\"undertekst\":\"undertekst\"," +
                "\"svartype\":\"svartype\"," +
                "\"min\":\"min\"," +
                "\"max\":\"max\"," +
                "\"kriterieForVisningAvUndersporsmal\":\"kriterieForVisningAvUndersporsmal\"," +
                "\"svar\":[{\"verdi\":\"svarverdi\"}]," +
                "\"undersporsmal\":[{\"id\":\"id\"," +
                "\"tag\":\"tag\"," +
                "\"sporsmalstekst\":\"sporsmalstekst\"," +
                "\"undertekst\":\"undertekst\"," +
                "\"svartype\":\"svartype\"," +
                "\"min\":\"min\"," +
                "\"max\":\"max\"," +
                "\"kriterieForVisningAvUndersporsmal\":\"kriterieForVisningAvUndersporsmal\"," +
                "\"svar\":[{\"verdi\":\"undersporsmal.svarverdi\"}]," +
                "\"undersporsmal\":[]}]}]," +
                "\"korrigerer\":\"korrigerer\"," +
                "\"korrigertAv\":\"korrigertAv\"}";


        SykepengesoknadSerializer serializer = new SykepengesoknadSerializer();

        byte[] bytes = serializer.serialize("topic", SykepengesoknadDTO.builder()
                .id("id")
                .aktorId("aktorId")
                .sykmeldingId("sykmeldingId")
                .soknadstype("soknadstype")
                .status("status")
                .fom(LocalDate.of(2018, 10, 15))
                .tom(LocalDate.of(2018, 10, 15))
                .opprettetDato(LocalDate.of(2018, 10, 15))
                .innsendtDato(LocalDate.of(2018, 10, 15))
                .sporsmal(singletonList(SporsmalDTO.builder()
                        .id("id")
                        .tag("tag")
                        .sporsmalstekst("sporsmalstekst")
                        .undertekst("undertekst")
                        .svartype("svartype")
                        .min("min")
                        .max("max")
                        .kriterieForVisningAvUndersporsmal("kriterieForVisningAvUndersporsmal")
                        .svar(singletonList(SvarDTO.builder()
                                .verdi("svarverdi")
                                .build()))
                        .undersporsmal(singletonList(SporsmalDTO.builder()
                                .id("id")
                                .tag("tag")
                                .sporsmalstekst("sporsmalstekst")
                                .undertekst("undertekst")
                                .svartype("svartype")
                                .min("min")
                                .max("max")
                                .kriterieForVisningAvUndersporsmal("kriterieForVisningAvUndersporsmal")
                                .svar(singletonList(SvarDTO.builder()
                                        .verdi("undersporsmal.svarverdi")
                                        .build()))
                                .undersporsmal(emptyList())
                                .build()))
                        .build()))
                .korrigerer("korrigerer")
                .korrigertAv("korrigertAv")
                .build());

        assertThat(bytes).containsExactly(s.getBytes());
    }
}

