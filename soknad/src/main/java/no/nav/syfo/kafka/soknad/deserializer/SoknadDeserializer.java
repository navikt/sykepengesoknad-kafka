package no.nav.syfo.kafka.soknad.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.nav.syfo.kafka.soknad.dto.SoknadDTO;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class SoknadDeserializer implements Deserializer<SoknadDTO> {
    private final ObjectMapper objectMapper;

    public SoknadDeserializer() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public SoknadDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, SoknadDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Feil ved konvertering av bytes til søknad", e);
        }
    }

    @Override
    public void close() {

    }
}
