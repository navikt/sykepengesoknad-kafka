package no.nav.syfo.kafka.sykepengesoknad.serializer;

import no.nav.syfo.kafka.sykepengesoknad.dto.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static no.nav.syfo.kafka.sykepengesoknad.TestUtil.serialisertSykepengesoknad;
import static no.nav.syfo.kafka.sykepengesoknad.dto.Arbeidssitasjon.ARBEIDSTAKER;
import static no.nav.syfo.kafka.sykepengesoknad.dto.FravarType.FERIE;
import static no.nav.syfo.kafka.sykepengesoknad.dto.InntektskildeType.FRILANSER;
import static no.nav.syfo.kafka.sykepengesoknad.dto.SoknadStatus.NY;
import static no.nav.syfo.kafka.sykepengesoknad.dto.SoknadType.ARBEIDSTAKERE;
import static no.nav.syfo.kafka.sykepengesoknad.dto.Svartype.CHECKBOX;
import static no.nav.syfo.kafka.sykepengesoknad.dto.Svartype.JA_NEI;
import static no.nav.syfo.kafka.sykepengesoknad.dto.Visningskritere.CHECKED;
import static no.nav.syfo.kafka.sykepengesoknad.dto.Visningskritere.JA;
import static org.assertj.core.api.Assertions.assertThat;

class SykepengesoknadDTOSerializerTest {

    @Test
    void test() {
        SykepengesoknadSerializer serializer = new SykepengesoknadSerializer();

        SykepengesoknadDTO sykepengesoknadDTO = SykepengesoknadDTO.builder()
                .id("34c434bb-64d9-464e-8224-3f3680abe046")
                .type(ARBEIDSTAKERE)
                .status(NY)
                .aktorId("111111111")
                .sykmeldingId("14e78e84-50a5-45bb-9919-191c54f99691")
                .orgnummer("999999999")
                .arbeidsgiver("ARBEIDSGIVER A/S")
                .arbeidssituasjon(ARBEIDSTAKER)
                .korrigerer("d79c7d92-30a0-497e-b72e-0476b46afa24")
                .korrigertAv("9090009c-d7fb-4d02-a97d-459a383fc5ed")
                .soktUtenlandsopphold(false)
                .fom(LocalDate.of(2018, 10, 15))
                .tom(LocalDate.of(2018, 10, 15))
                .startSykeforlop(LocalDate.of(2018, 10, 15))
                .sykmeldingUtskrevet(LocalDate.of(2018, 10, 15))
                .arbeidGjenopptatt(LocalDate.of(2018, 10, 16))
                .opprettet(LocalDateTime.of(2018, 10, 15, 9, 0))
                .sendtNav(LocalDateTime.of(2018, 10, 15, 10, 2))
                .sendtArbeidsgiver(LocalDateTime.of(2018, 10, 15, 10, 0))
                .egenmeldinger(singletonList(PeriodeDTO.builder()
                        .fom(LocalDate.of(2018, 10, 10))
                        .tom(LocalDate.of(2018, 10, 14))
                        .build()))
                .papirsykmeldinger(singletonList(PeriodeDTO.builder()
                        .fom(LocalDate.of(2018, 10, 5))
                        .tom(LocalDate.of(2018, 10, 9))
                        .build()))
                .fravar(singletonList(FravarDTO.builder()
                        .fom(LocalDate.of(2018, 10, 11))
                        .tom(LocalDate.of(2018, 10, 12))
                        .type(FERIE)
                        .build()))
                .andreInntektskilder(singletonList(InntektskildeDTO.builder()
                        .type(FRILANSER)
                        .sykmeldt(true)
                        .build()))
                .soknadPerioder(singletonList(SoknadPeriodeDTO.builder()
                        .fom(LocalDate.of(2018, 10, 15))
                        .tom(LocalDate.of(2018, 10, 15))
                        .sykmeldingGrad(100)
                        .faktiskGrad(90)
                        .avtaltTimer(37.5)
                        .faktiskTimer(33.75)
                        .build()))
                .sporsmal(singletonList(SporsmalDTO.builder()
                        .id("id")
                        .tag("tag")
                        .sporsmalstekst("sporsmalstekst")
                        .undertekst("undertekst")
                        .svartype(JA_NEI)
                        .min("min")
                        .max("max")
                        .kriterieForVisningAvUndersporsmal(JA)
                        .svar(singletonList(SvarDTO.builder()
                                .verdi("svarverdi")
                                .build()))
                        .undersporsmal(singletonList(SporsmalDTO.builder()
                                .id("id")
                                .tag("tag")
                                .sporsmalstekst("sporsmalstekst")
                                .undertekst("undertekst")
                                .svartype(CHECKBOX)
                                .min("min")
                                .max("max")
                                .kriterieForVisningAvUndersporsmal(CHECKED)
                                .svar(singletonList(SvarDTO.builder()
                                        .verdi("undersporsmal.svarverdi")
                                        .build()))
                                .undersporsmal(emptyList())
                                .build()))
                        .build()))
                .build();

        byte[] bytes = serializer.serialize("topic", sykepengesoknadDTO);

        assertThat(bytes).containsExactly(serialisertSykepengesoknad.getBytes());
    }
}

