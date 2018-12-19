package no.nav.syfo.kafka.soknad.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.function.Function;

/**
 * <p>{@link FunctionSerializer} brukes til å serialisere en melding ved hjelp av en {@link Function}</p>
 *
 * <p>Når meldingen skal serialiseres til <tt>byte[]</tt>, så kalles {@link FunctionSerializer#serializer}
 * med meldingen som input</p>
 *
 * <p>Eksempel som vil serialisere meldingen til <tt>byte[]</tt>:</p>
 * <p>{@code new FunctionSerializer<String>(melding -> melding.getBytes())}</p>
 *
 * @param <T> typen det skal serialiseres fra
 */
public class FunctionSerializer<T> implements Serializer<T> {
    private final Function<T, byte[]> serializer;

    public FunctionSerializer(Function<T, byte[]> serializer) {
        this.serializer = serializer;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    /**
     * <p>Serialiserer meldingen med {@link FunctionSerializer#serializer}</p>
     *
     * @param topic topic som dataene skal til
     * @param t     melding som skal serialiseres
     * @return serialisert melding. Returnerer <tt>null</tt> hvis t er <tt>null</tt>
     */
    @Override
    public byte[] serialize(String topic, T t) {
        if (t == null) {
            return null;
        }
        try {
            return serializer.apply(t);
        } catch (Exception e) {
            throw new RuntimeException("Feil ved konvertering av søknad til bytes", e);
        }
    }

    @Override
    public void close() {
    }
}
