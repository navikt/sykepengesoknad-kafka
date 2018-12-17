package no.nav.syfo.kafka.sykepengesoknad.deserializer;

import no.nav.syfo.kafka.sykepengesoknad.dto.SykepengesoknadDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SykepengesoknadDeserializerTest {
    @Test
    void full() {
        String s = "{\"id\":\"id\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettetDato\":\"2018-10-15\"," +
                "\"innsendtDato\":\"2018-10-15\"," +
                "\"startSykeforlop\":\"2018-10-15\"," +
                "\"sykmeldingUtskrevet\":\"2018-10-15\"," +
                "\"arbeidsgiver\":\"ARBEIDSGIVER A/S\"," +
                "\"arbeidssituasjon\":\"ARBEIDSTAKER\"," +
                "\"korrigerer\":\"korrigerer\"," +
                "\"korrigertAv\":\"korrigertAv\"," +
                "\"soknadPerioder\":[{\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"grad\":100}]," +
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
                "\"undersporsmal\":[]}]}]}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", s.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSoknadPerioder().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0).getUndersporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0).getSvar().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    void taklerAtNyeFelterMangler() {
        String s = "{\"id\":\"id\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettetDato\":\"2018-10-15\"," +
                "\"innsendtDato\":\"2018-10-15\"," +
                "\"korrigerer\":\"korrigerer\"," +
                "\"korrigertAv\":\"korrigertAv\"," +
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
                "\"undersporsmal\":[]}]}]}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", s.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrPropertiesExcept("startSykeforlop", "sykmeldingUtskrevet", "arbeidsgiver", "arbeidssituasjon", "soknadPerioder");
    }

    @Test
    void ignoreUnknown() {
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
                "\"startSykeforlop\":\"2018-10-15\"," +
                "\"sykmeldingUtskrevet\":\"2018-10-15\"," +
                "\"arbeidsgiver\":\"ARBEIDSGIVER A/S\"," +
                "\"arbeidssituasjon\":\"ARBEIDSTAKER\"," +
                "\"korrigerer\":\"korrigerer\"," +
                "\"korrigertAv\":\"korrigertAv\"," +
                "\"soknadPerioder\":[{\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"grad\":100}]," +
                "\"sporsmal\":[{\"id\":null," +
                "\"tag\":null," +
                "\"sporsmalstekst\":null," +
                "\"undertekst\":null," +
                "\"svartype\":null," +
                "\"min\":null," +
                "\"max\":null," +
                "\"kriterieForVisningAvUndersporsmal\":null," +
                "\"svar\":null," +
                "\"undersporsmal\":null}]}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", s.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrProperties();
    }
}

