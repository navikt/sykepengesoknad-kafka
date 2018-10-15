package no.nav.syfo.kafka;

import org.apache.kafka.common.header.internals.RecordHeader;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Collections.singletonMap;
import static java.util.stream.Stream.of;
import static no.nav.syfo.kafka.KafkaHeaderConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SyfoProducerRecordTest {
    @Test
    public void noExtraHeaders() {
        SyfoProducerRecord<String, String> producerRecord = new SyfoProducerRecord<>("topic", "key", "value");

        assertThat(producerRecord.headers()).allMatch(header ->
                of(GUID, CALL_ID, TYPE, CREATED_DATE).anyMatch(key -> key.equals(header.key())));

        assertThat(new String(producerRecord.headers().lastHeader(GUID).value())).isNotNull();
        assertThat(new String(producerRecord.headers().lastHeader(TYPE).value())).isEqualTo("String");
        assertThat(new String(producerRecord.headers().lastHeader(CREATED_DATE).value())).startsWith(LocalDate.now().toString());
    }

    @Test
    public void additionalHeaderInConstructor() {
        SyfoProducerRecord<String, String> producerRecord = new SyfoProducerRecord<>("topic", "key", "value", singletonMap("testHeader", "test"));

        assertThat(producerRecord.headers()).allMatch(header ->
                of(GUID, CALL_ID, TYPE, CREATED_DATE, "testHeader").anyMatch(key -> key.equals(header.key())));

        assertThat(producerRecord.headers()).contains(new RecordHeader("testHeader", "test".getBytes(UTF_8)));
    }

    @Test
    public void additionalHeaderPostConstruct() {
        SyfoProducerRecord<String, String> producerRecord = new SyfoProducerRecord<>("topic", "key", "value");

        producerRecord.addHeaders(singletonMap("testHeader", "test"));

        assertThat(producerRecord.headers()).allMatch(header ->
                of(GUID, CALL_ID, TYPE, CREATED_DATE, "testHeader").anyMatch(key -> key.equals(header.key())));

        assertThat(producerRecord.headers()).contains(new RecordHeader("testHeader", "test".getBytes(UTF_8)));
    }
}