package no.nav.syfo.kafka;

import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

public final class KafkaHeaderConstants {
    public static final String GUID = "guid";
    public static final String TYPE = "type";
    public static final String CREATED_DATE = "createdDate";
    public static final String CALL_ID = "callId";
    public static final String NAV_CALLID = "Nav-Callid";
    public static final String MELDINGSTYPE = "MELDINGSTYPE";

    public static Optional<String> getLastHeaderByKeyAsString(Headers headers, String key) {
        Header header = headers.lastHeader(key);
        if (header != null && header.value() != null) {
            return Optional.of(new String(header.value(), UTF_8));
        }
        return Optional.empty();
    }
}
