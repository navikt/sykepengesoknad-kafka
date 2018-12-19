package no.nav.syfo.kafka.soknad.deserializer;

import no.nav.syfo.kafka.interfaces.Soknad;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.junit.jupiter.api.Test;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.*;
import static no.nav.syfo.kafka.KafkaHeaderConstants.MELDINGSTYPE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MultiFunctionDeserializerTest {

    private class Testsoknad implements Soknad {
        public String id;

        public Testsoknad(String id) {
            this.id = id;
        }
    }

    @Test
    void testDefault() {
        String s = "id";

        MultiFunctionDeserializer<Testsoknad> deserializer = new MultiFunctionDeserializer<>(emptyMap(),
                bytes -> new Testsoknad(new String(bytes, UTF_8)));

        Testsoknad testsoknad = deserializer.deserialize("topic",
                new RecordHeaders(singletonList(
                        new RecordHeader(MELDINGSTYPE, "spesifikk".getBytes(UTF_8)))), s.getBytes());

        assertThat(testsoknad.id).isEqualTo("id");
    }

    @Test
    void testIngenDefault() {
        String s = "id";

        MultiFunctionDeserializer<Testsoknad> deserializer = new MultiFunctionDeserializer<>(emptyMap());

        assertThatThrownBy(() -> deserializer.deserialize("topic",
                new RecordHeaders(singletonList(
                        new RecordHeader(MELDINGSTYPE, "spesifikk".getBytes(UTF_8)))), s.getBytes()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Default deserializer er ikke definert")
                .hasNoCause();
    }

    @Test
    void testFeilIDefault() {
        String s = "id";

        MultiFunctionDeserializer<Testsoknad> deserializer = new MultiFunctionDeserializer<>(emptyMap(),
                bytes -> {
                    throw new IllegalArgumentException();
                });

        assertThatThrownBy(() -> deserializer.deserialize("topic",
                new RecordHeaders(singletonList(
                        new RecordHeader(MELDINGSTYPE, "spesifikk".getBytes(UTF_8)))), s.getBytes()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Feil ved konvertering av bytes til søknad")
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testSpesifikk() {
        String s = "id";

        MultiFunctionDeserializer<Testsoknad> deserializer = new MultiFunctionDeserializer<>(singletonMap("spesifikk",
                ((headers, bytes) -> new Testsoknad(new String(bytes, UTF_8)))));

        Testsoknad testsoknad = deserializer.deserialize("topic",
                new RecordHeaders(singletonList(
                        new RecordHeader(MELDINGSTYPE, "spesifikk".getBytes(UTF_8)))), s.getBytes());

        assertThat(testsoknad.id).isEqualTo("id");
    }

    @Test
    void testFeilISpesifikk() {
        String s = "id";

        MultiFunctionDeserializer<Testsoknad> deserializer = new MultiFunctionDeserializer<>(singletonMap("spesifikk",
                ((headers, bytes) -> {
                    throw new IllegalArgumentException();
                })));

        assertThatThrownBy(() -> deserializer.deserialize("topic",
                new RecordHeaders(singletonList(
                        new RecordHeader(MELDINGSTYPE, "spesifikk".getBytes(UTF_8)))), s.getBytes()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Feil ved konvertering av bytes til søknad")
                .hasCauseInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testSpesifikkMatcherIkkeBrukerDefault() {
        String s = "id";

        MultiFunctionDeserializer<Testsoknad> deserializer = new MultiFunctionDeserializer<>(singletonMap("ikkeRiktigFunksjon",
                ((headers, bytes) -> new Testsoknad("ikkeDenne"))),
                bytes -> new Testsoknad(new String(bytes, UTF_8)));

        Testsoknad testsoknad = deserializer.deserialize("topic",
                new RecordHeaders(singletonList(
                        new RecordHeader(MELDINGSTYPE, "spesifikk".getBytes(UTF_8)))), s.getBytes());

        assertThat(testsoknad.id).isEqualTo("id");
    }
}
