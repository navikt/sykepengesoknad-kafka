package no.nav.syfo.kafka.sykepengesoknad.deserializer;

import no.nav.syfo.kafka.sykepengesoknad.dto.SykepengesoknadDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SykepengesoknadDeserializerTest {
    @Test
    public void full() {
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

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", s.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0).getUndersporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0).getSvar().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    public void oneMissing() {
        String s = "{\"id\":\"id\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettetDato\":\"2018-10-15\"," +
                "\"innsendtDato\":\"2018-10-15\"," +
                "\"sporsmal\":[{\"id\":null," +
                "\"tag\":null," +
                "\"sporsmalstekst\":null," +
                "\"undertekst\":null," +
                "\"svartype\":null," +
                "\"min\":null," +
                "\"max\":null," +
                "\"kriterieForVisningAvUndersporsmal\":null," +
                "\"svar\":null," +
                "\"undersporsmal\":null}]," +
                "\"korrigerer\":\"korrigerer\"," +
                "\"korrigertAv\":\"korrigertAv\"}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", s.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrPropertiesExcept("aktorId");
    }

    @Test
    public void ignoreUnknown() {
        String s = "{\"id\":\"id\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"fnr\":\"fnr\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettetDato\":\"2018-10-15\"," +
                "\"innsendtDato\":\"2018-10-15\"," +
                "\"sporsmal\":[{\"id\":null," +
                "\"tag\":null," +
                "\"sporsmalstekst\":null," +
                "\"undertekst\":null," +
                "\"svartype\":null," +
                "\"min\":null," +
                "\"max\":null," +
                "\"kriterieForVisningAvUndersporsmal\":null," +
                "\"svar\":null," +
                "\"undersporsmal\":null}]," +
                "\"korrigerer\":\"korrigerer\"," +
                "\"korrigertAv\":\"korrigertAv\"}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", s.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrProperties();
    }
}

