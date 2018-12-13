package no.nav.syfo.kafka.sykepengesoknad.serializer;

import no.nav.syfo.kafka.sykepengesoknad.dto.SoknadPeriodeDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.SoknadSporsmalDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.SporsmalDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.SvarDTO;
import no.nav.syfo.kafka.sykepengesoknad.dto.marius.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

class SykepengesoknadDTOSerializerTest {


    @Test
    void test() {
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
                "\"egenmeldinger\":[{\"fom\":\"2018-10-10\"," +
                "\"tom\":\"2018-10-14\"}]," +
                "\"papirsykmeldinger\":[{\"fom\":\"2018-10-05\"," +
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


        SykepengesoknadSerializer serializer = new SykepengesoknadSerializer();

        SoknadSporsmalDTO soknadSporsmalDTO = SoknadSporsmalDTO.builder()
                .id("id")
                .aktorId("aktorId")
                .sykmeldingId("sykmeldingId")
                .soknadstype("soknadstype")
                .status("status")
                .fom(LocalDate.of(2018, 10, 15))
                .tom(LocalDate.of(2018, 10, 15))
                .opprettetDato(LocalDate.of(2018, 10, 15))
                .innsendtDato(LocalDate.of(2018, 10, 15))
                .startSykeforlop(LocalDate.of(2018, 10, 15))
                .sykmeldingUtskrevet(LocalDate.of(2018, 10, 15))
                .arbeidsgiver("ARBEIDSGIVER A/S")
                .arbeidssituasjon("ARBEIDSTAKER")
                .korrigerer("korrigerer")
                .korrigertAv("korrigertAv")
                .soknadPerioder(singletonList(SoknadPeriodeDTO.builder()
                        .fom(LocalDate.of(2018, 10, 15))
                        .tom(LocalDate.of(2018, 10, 15))
                        .grad(100)
                        .build()))
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
                .build();

        SykepengesoknadDTO sykepengesoknadDTO = SykepengesoknadDTO.builder()
                .aktorId("aktorId")
                .andreInntektskilder(singletonList(Inntektskilde.builder()
                        .navn("ARBEIDSGIVER A/S")
                        .sykemeldt(true)
                        .build()))
                .egenmeldinger(singletonList(Periode.builder()
                        .fom(LocalDate.of(2018, 10, 10))
                        .tom(LocalDate.of(2018, 10, 14))
                        .build()))
                .papirsykmeldinger(singletonList(Periode.builder()
                        .fom(LocalDate.of(2018, 10, 5))
                        .tom(LocalDate.of(2018, 10, 9))
                        .build()))
                .fravaer(singletonList(Fravar.builder()
                        .fom(LocalDate.of(2018, 10, 11))
                        .tom(LocalDate.of(2018, 10, 12))
                        .type("FERIE")
                        .build()))
                .korrigertArbeidstidListe(singletonList(KorrigertArbeidstid.builder()
                        .avtalt_timer(37.5)
                        .faktisk_grad(67d)
                        .faktisk_timer(25d)
                        .fom(LocalDate.of(2018, 10, 15))
                        .tom(LocalDate.of(2018, 10, 15))
                        .build()))
                .orgnummer("11122233344")
                .soknadstype("soknadstype")
                .sykmeldingId("sykmeldingId")
                .soktUtenlandsopphold(false)
                .soknadSporsmalDTO(soknadSporsmalDTO)
                .build();

        byte[] bytes = serializer.serialize("topic", sykepengesoknadDTO);

        assertThat(bytes).containsExactly(serialisert.getBytes());
    }
}

