package no.nav.syfo.kafka.sykepengesoknad.deserializer;

import no.nav.syfo.kafka.sykepengesoknad.dto.SoknadSporsmalDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.marius.SykepengesoknadDTO;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SykepengesoknadDTODeserializerTest {

    @Test
    void full() {
        String serialisert = "{\"soknadstype\":\"soknadstype\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"orgnummer\":\"11122233344\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"korrigertArbeidstidListe\":[{\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"avtalt_timer\":37.5," +
                "\"faktisk_grad\":67.0," +
                "\"faktisk_timer\":25.0}]," +
                "\"fravaer\":[{\"fom\":\"2018-10-11\"," +
                "\"tom\":\"2018-10-12\"," +
                "\"type\":\"FERIE\"}]," +
                "\"soktUtenlandsopphold\":false," +
                "\"egenmeldinger\":null," +
                "\"papirsykmeldinger\":null," +
                "\"annenSykmeldingListe\":[{\"fom\":\"2018-10-05\"," +
                "\"tom\":\"2018-10-09\"}]," +
                "\"andreInntektskilder\":[{\"navn\":\"ARBEIDSGIVER A/S\"," +
                "\"sykemeldt\":true}]," +
                "\"soknadSporsmalDTO\":{\"id\":\"id\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettet\":\"2018-10-15\"," +
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
                "\"undersporsmal\":[]}]}]}}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", serialisert.getBytes());

        assertThat(sykepengesoknadDTO.getOrgnummer()).isEqualTo("11122233344");

        SoknadSporsmalDTO soknadsdto = sykepengesoknadDTO.getSoknadSporsmalDTO();
        assertThat(soknadsdto.getSoknadPerioder().get(0)).hasNoNullFieldsOrProperties();
        assertThat(soknadsdto.getSporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(soknadsdto.getSporsmal().get(0).getUndersporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(soknadsdto.getSporsmal().get(0).getSvar().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    void taklerAtNyeFelterMangler() {
        String serialisert = "{\"soknadstype\":\"soknadstype\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"korrigertArbeidstidListe\":[{\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"avtalt_timer\":37.5," +
                "\"faktisk_grad\":67.0," +
                "\"faktisk_timer\":25.0}]," +
                "\"fravaer\":[{\"fom\":\"2018-10-11\"," +
                "\"tom\":\"2018-10-12\"," +
                "\"type\":\"FERIE\"}]," +
                "\"soktUtenlandsopphold\":false," +
                "\"egenmeldinger\":null," +
                "\"papirsykmeldinger\":null," +
                "\"annenSykmeldingListe\":[{\"fom\":\"2018-10-05\"," +
                "\"tom\":\"2018-10-09\"}]," +
                "\"andreInntektskilder\":[{\"navn\":\"ARBEIDSGIVER A/S\"," +
                "\"sykemeldt\":true}]," +
                "\"soknadSporsmalDTO\":{\"id\":\"id\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettet\":\"2018-10-15\"," +
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
                "\"undersporsmal\":[]}]}]}}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", serialisert.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrPropertiesExcept("orgnummer", "egenmeldinger", "papirsykmeldinger");
    }

    @Test
    void ignoreUnknown() {
        String serialisert = "{\"soknadstype\":\"soknadstype\"," +
                "\"aktorIdent\":\"afdfgfgktorId\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"orgnummer\":\"11122233344\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"korrigertArbeidstidListe\":[{\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"avtalt_timer\":37.5," +
                "\"faktisk_grad\":67.0," +
                "\"faktisk_timer\":25.0}]," +
                "\"fravaer\":[{\"fom\":\"2018-10-11\"," +
                "\"tom\":\"2018-10-12\"," +
                "\"type\":\"FERIE\"}]," +
                "\"soktUtenlandsopphold\":false," +
                "\"egenmeldinger\":[{\"fom\":\"2018-10-10\"," +
                "\"tom\":\"2018-10-14\"}]," +
                "\"papirsykmeldinger\":[{\"fom\":\"2018-10-05\"," +
                "\"tom\":\"2018-10-09\"}]," +
                "\"annenSykmeldingListe\":[{\"fom\":\"2018-10-05\"," +
                "\"tom\":\"2018-10-09\"}]," +
                "\"andreInntektskilder\":[{\"navn\":\"ARBEIDSGIVER A/S\"," +
                "\"sykemeldt\":true}]," +
                "\"soknadSporsmalDTO\":{\"id\":\"id\"," +
                "\"aktorId\":\"aktorId\"," +
                "\"sykmeldingId\":\"sykmeldingId\"," +
                "\"soknadstype\":\"soknadstype\"," +
                "\"status\":\"status\"," +
                "\"fom\":\"2018-10-15\"," +
                "\"tom\":\"2018-10-15\"," +
                "\"opprettet\":\"2018-10-15\"," +
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
                "\"undersporsmal\":[]}]}]}}";

        SykepengesoknadDeserializer deserializer = new SykepengesoknadDeserializer();

        SykepengesoknadDTO sykepengesoknadDTO = deserializer.deserialize("topic", serialisert.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrProperties();
    }
}

