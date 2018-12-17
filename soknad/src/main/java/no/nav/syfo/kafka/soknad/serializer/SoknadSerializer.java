package no.nav.syfo.kafka.soknad.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.nav.syfo.kafka.soknad.dto.SoknadDTO;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class SoknadSerializer implements Serializer<SoknadDTO> {
    private final ObjectMapper objectMapper;

    public SoknadSerializer() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, SoknadDTO soknad) {
        if (soknad == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(soknad);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Feil ved konvertering av søknad til bytes", e);
        }
    }

    @Override
    public void close() {
    }
}
