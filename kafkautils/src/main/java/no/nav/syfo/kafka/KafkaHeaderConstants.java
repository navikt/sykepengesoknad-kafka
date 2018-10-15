package no.nav.syfo.kafka;

import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class KafkaHeaderConstants {
    public static final String GUID = "guid";
    public static final String TYPE = "type";
    public static final String CREATED_DATE = "createdDate";
    public static final String CALL_ID = "callId";

    public static String getLastHeaderByKeyAsString(Headers headers, String key, String defaultValue) {
        Header header = headers.lastHeader(key);
        if (header != null && header.value() != null) {
            return new String(header.value(), UTF_8);
        }
        return defaultValue;
    }
}