package no.nav.syfo.kafka.soknad.deserializer;

import no.nav.syfo.kafka.interfaces.Soknad;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.ExtendedDeserializer;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static no.nav.syfo.kafka.KafkaHeaderConstants.MELDINGSTYPE;
import static no.nav.syfo.kafka.KafkaHeaderConstants.getLastHeaderByKeyAsString;

public class SoknadDeserializer<T extends Soknad> implements ExtendedDeserializer<T> {
    private final Map<String, BiFunction<Headers, byte[], T>> deserializeMap;
    private final Function<byte[], T> deserializeDefault;

    public SoknadDeserializer(final Map<String, BiFunction<Headers, byte[], T>> deserializeMap) {
        this(deserializeMap, null);
    }

    public SoknadDeserializer(final Map<String, BiFunction<Headers, byte[], T>> deserializeMap, final Function<byte[], T> deserializeDefault) {
        this.deserializeMap = deserializeMap;
        this.deserializeDefault = deserializeDefault;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] bytes) {
        return getLastHeaderByKeyAsString(headers, MELDINGSTYPE)
                .map(deserializeMap::get)
                .map(f -> {
                    try {
                        return f.apply(headers, bytes);
                    } catch (Exception e) {
                        throw new RuntimeException("Feil ved konvertering av bytes til søknad", e);
                    }
                })
                .orElseGet(() -> deserialize(topic, bytes));
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (deserializeDefault == null)
            throw new IllegalArgumentException("Default deserializer er ikke definert");
        try {
            return deserializeDefault.apply(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Feil ved konvertering av bytes til søknad", e);
        }
    }

    @Override
    public void close() {

    }
}
