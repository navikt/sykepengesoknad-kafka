package no.nav.syfo.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.slf4j.MDC;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Objects.requireNonNull;
import static no.nav.syfo.kafka.KafkaHeaderConstants.*;

public class SyfoProducerRecord<K, V> extends ProducerRecord<K, V> {

    public SyfoProducerRecord(String topic, K key, V value) {
        this(topic, key, value, emptyMap());
    }

    public SyfoProducerRecord(String topic, K key, V value, Map<String, Object> headers) {
        super(topic, null, System.currentTimeMillis(), key, value, defaultHeaders(value, headers));
    }

    public void addHeaders(Map<String, Object> headers) {
        headers.entrySet()
                .stream()
                .map(entry -> new RecordHeader(entry.getKey(), toUtf8Bytes(entry.getValue())))
                .forEach(headers()::add);
    }

    private static <V> Iterable<Header> defaultHeaders(V value, Map<String, Object> additionalHeaders) {
        requireNonNull(value);
        Header guid = new RecordHeader(GUID, toUtf8Bytes(UUID.randomUUID()));
        Header trackingId = new RecordHeader(CALL_ID, toUtf8Bytes(MDC.get(CALL_ID)));
        Header type = new RecordHeader(TYPE, toUtf8Bytes(value.getClass().getSimpleName()));
        Header createdDate = new RecordHeader(CREATED_DATE, toUtf8Bytes(LocalDateTime.now()));
        List<Header> headers = new ArrayList<>(asList(type, createdDate, guid, trackingId));

        additionalHeaders.entrySet()
                .stream()
                .map(entry -> new RecordHeader(entry.getKey(), toUtf8Bytes(entry.getValue())))
                .forEach(headers::add);

        return headers;
    }

    private static byte[] toUtf8Bytes(Object object) {
        if (object != null) {
            return object.toString().getBytes(UTF_8);
        }
        return new byte[0];
    }
}
