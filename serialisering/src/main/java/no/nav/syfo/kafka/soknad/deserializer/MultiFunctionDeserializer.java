package no.nav.syfo.kafka.soknad.deserializer;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static no.nav.syfo.kafka.KafkaHeaderConstants.MELDINGSTYPE;
import static no.nav.syfo.kafka.KafkaHeaderConstants.getLastHeaderByKeyAsString;


/**
 * <p>{@link MultiFunctionDeserializer} brukes for å kunne ha flere meldingstyper på samme kafkatopic.
 * Ved å angi ulike mapperfunksjoner i {@link MultiFunctionDeserializer#deserializeMap deserializeMap}
 * vil deserializeren velge den mapperfunksjonen som har samme key som
 * {@link no.nav.syfo.kafka.KafkaHeaderConstants#MELDINGSTYPE MELDINGSTYPE}-headeren i meldingen.</p>
 *
 * <p>Hvis {@link no.nav.syfo.kafka.KafkaHeaderConstants#MELDINGSTYPE MELDINGSTYPE}-headeren ikke er
 * satt i meldingen, eller det ikke finnes en mapperfunksjon med samme key, så brukes
 * {@link MultiFunctionDeserializer#deserializeDefault deserializeDefault} i stedet</p>
 *
 * <p>Eksempel som vil mappe meldingen til en String, hvis
 * {@link no.nav.syfo.kafka.KafkaHeaderConstants#MELDINGSTYPE MELDINGSTYPE}-headeren == "meldingstype":</p>
 * <p><tt>new MultiFunctionDeserializer(Collections.singletonMap("meldingstype",
 * (headers, melding) -> new String(melding)))</tt></p>
 *
 * @param <T> typen det skal deserialiseres til
 */
public class MultiFunctionDeserializer<T> implements Deserializer<T> {
    private final Map<String, BiFunction<Headers, byte[], T>> deserializeMap;
    private final Function<byte[], T> deserializeDefault;

    /**
     * <p>Oppretter en {@link MultiFunctionDeserializer} uten
     * {@link MultiFunctionDeserializer#deserializeDefault deserializeDefault}</p>
     *
     * <p>Ved deserialisering av ukjente meldinger vil {@link MultiFunctionDeserializer#deserialize(String, Headers, byte[])}
     * kaste {@link IllegalArgumentException}</p>
     *
     * <p>Eksempel som vil mappe meldingen til en String, hvis
     * {@link no.nav.syfo.kafka.KafkaHeaderConstants#MELDINGSTYPE MELDINGSTYPE}-headeren == "meldingstype":</p>
     * <p>{@code new MultiFunctionDeserializer<String>(Collections.singletonMap("meldingstype",
     * (headers, melding) -> new String(melding)))}</p>
     *
     * @param deserializeMap map med key som matcher
     *                       {@link no.nav.syfo.kafka.KafkaHeaderConstants#MELDINGSTYPE MELDINGSTYPE}-headeren i meldingen
     * @code new {@link MultiFunctionDeserializer <String>}
     */
    public MultiFunctionDeserializer(final Map<String, BiFunction<Headers, byte[], T>> deserializeMap) {
        this(deserializeMap, null);
    }

    /**
     * <p>Oppretter en {@link MultiFunctionDeserializer} med {@link MultiFunctionDeserializer#deserializeDefault deserializeDefault}</p>
     *
     * <p>Ved deserialisering av ukjente meldinger vil {@link MultiFunctionDeserializer#deserializeDefault deserializeDefault}
     * benyttes for deserialisering</p>
     *
     * @param deserializeMap     map med key som matcher
     *                           {@link no.nav.syfo.kafka.KafkaHeaderConstants#MELDINGSTYPE MELDINGSTYPE}-headeren i meldingen
     * @param deserializeDefault default mapperfunksjon som brukes hvis ingen spesifikk blir funnet
     */
    public MultiFunctionDeserializer(final Map<String, BiFunction<Headers, byte[], T>> deserializeMap, final Function<byte[], T> deserializeDefault) {
        this.deserializeMap = deserializeMap;
        this.deserializeDefault = deserializeDefault;
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    /**
     * <p>Deserialiserer meldingen med den mapperfunksjonen som matcher med
     * {@link no.nav.syfo.kafka.KafkaHeaderConstants#MELDINGSTYPE MELDINGSTYPE}-headeren i meldingen.</p>
     *
     * <p>Hvis ingen mapperfunksjon blir funnet, brukes
     * {@link MultiFunctionDeserializer#deserializeDefault deserializeDefault} i stedet</p>
     *
     * @param topic   topic som dataene kommer fra
     * @param headers headerne til meldingen. Kan være null
     * @param bytes   serialisert melding. Kan være null
     * @return deserialisert melding. Kan være null
     */
    @Override
    public T deserialize(String topic, Headers headers, byte[] bytes) {
        return getLastHeaderByKeyAsString(headers, MELDINGSTYPE)
                .map(deserializeMap::get)
                .map(f -> {
                    try {
                        return f.apply(headers, bytes);
                    } catch (Exception e) {
                        throw new RuntimeException("Feil ved deserialisering av bytes", e);
                    }
                })
                .orElseGet(() -> deserialize(topic, bytes));
    }

    /**
     * <p>Deserialiserer meldingen med {@link MultiFunctionDeserializer#deserializeDefault deserializeDefault}</p>
     *
     * @param topic topic som dataene kommer fra
     * @param bytes serialisert melding. Kan være null
     * @return deserialisert melding. Kan være null
     * @throws IllegalArgumentException hvis {@link MultiFunctionDeserializer#deserializeDefault deserializeDefault} er null
     */
    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (deserializeDefault == null)
            throw new IllegalArgumentException("Default mapperfunksjon er ikke definert");
        try {
            return deserializeDefault.apply(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Feil ved deserialisering av bytes", e);
        }
    }

    @Override
    public void close() {

    }
}
