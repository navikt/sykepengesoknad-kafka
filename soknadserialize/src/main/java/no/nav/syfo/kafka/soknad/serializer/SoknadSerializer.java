package no.nav.syfo.kafka.soknad.serializer;

import no.nav.syfo.kafka.interfaces.Soknad;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.function.Function;

public class SoknadSerializer<T extends Soknad> implements Serializer<T> {
    private final Function<T, byte[]> function;

    public SoknadSerializer(Function<T, byte[]> function) {
        this.function = function;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, T t) {
        if (t == null) {
            return null;
        }
        try {
            return function.apply(t);
        } catch (Exception e) {
            throw new RuntimeException("Feil ved konvertering av søknad til bytes", e);
        }
    }

    @Override
    public void close() {
    }
}
