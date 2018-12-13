package no.nav.syfo.kafka.sykepengesoknad.deserializer;

import no.nav.syfo.kafka.sykepengesoknad.dto.SykepengesoknadDTO;
import org.junit.jupiter.api.Test;

import static no.nav.syfo.kafka.sykepengesoknad.TestUtil.serialisertSykepengesoknad;
import static no.nav.syfo.kafka.sykepengesoknad.dto.SoknadType.UNKNOWN;
import static org.assertj.core.api.Assertions.assertThat;

class SykepengesoknadDTODeserializerTest {

    private SykepengesoknadDeserializer sykepengesoknadDeserializer = new SykepengesoknadDeserializer();

    @Test
    void deserialisererFullSykepengesoknad() {
        SykepengesoknadDTO sykepengesoknadDTO = sykepengesoknadDeserializer.deserialize("topic", serialisertSykepengesoknad.getBytes());

        assertThat(sykepengesoknadDTO.getOrgnummer()).isEqualTo("999999999");
        assertThat(sykepengesoknadDTO.getSoknadPerioder().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0).getUndersporsmal().get(0)).hasNoNullFieldsOrProperties();
        assertThat(sykepengesoknadDTO.getSporsmal().get(0).getSvar().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    void taklerAtNyeFelterMangler() {
        String serialisertSykepengesoknadUtenOrgnummer = serialisertSykepengesoknad.replace("\"orgnummer\":\"999999999\",", "");

        SykepengesoknadDTO sykepengesoknadDTO = sykepengesoknadDeserializer.deserialize("topic", serialisertSykepengesoknadUtenOrgnummer.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrPropertiesExcept("orgnummer");
    }

    @Test
    void ignoreUnknown() {
        String serialisertSykepengesoknadMedUkjentFelt = serialisertSykepengesoknad.replaceFirst(",", ",\"aktorIdent\":\"111111111\",");

        SykepengesoknadDTO sykepengesoknadDTO = sykepengesoknadDeserializer.deserialize("topic", serialisertSykepengesoknadMedUkjentFelt.getBytes());

        assertThat(sykepengesoknadDTO).hasNoNullFieldsOrProperties();
    }

    @Test
    void ukjentEnumDeserialiseresSomUNKOWN() {
        String serialisertSykepengesoknadMedNySoknadType = serialisertSykepengesoknad.replace("\"type\":\"ARBEIDSTAKERE\",", "\"type\":\"EN_NY_SOKNADTYPE\",");

        SykepengesoknadDTO sykepengesoknadDTO = sykepengesoknadDeserializer.deserialize("topic", serialisertSykepengesoknadMedNySoknadType.getBytes());

        assertThat(sykepengesoknadDTO.getType()).isEqualTo(UNKNOWN);
    }

}

