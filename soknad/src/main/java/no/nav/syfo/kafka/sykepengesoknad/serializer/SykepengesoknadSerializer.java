package no.nav.syfo.kafka.sykepengesoknad.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.nav.syfo.kafka.sykepengesoknad.dto.SykepengesoknadDTO;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public class SykepengesoknadSerializer implements Serializer<SykepengesoknadDTO> {
    private final ObjectMapper objectMapper;

    SykepengesoknadSerializer() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, SykepengesoknadDTO sykepengesoknad) {
        if (sykepengesoknad == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(sykepengesoknad);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Feil ved konvertering av Sykepengesøknad til bytes", e);
        }
    }

    @Override
    public void close() {
    }
}
