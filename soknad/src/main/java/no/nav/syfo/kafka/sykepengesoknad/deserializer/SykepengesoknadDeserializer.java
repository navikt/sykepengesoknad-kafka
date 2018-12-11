package no.nav.syfo.kafka.sykepengesoknad.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import no.nav.syfo.kafka.sykepengesoknad.dto.SoknadSporsmalDTO;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class SykepengesoknadDeserializer implements Deserializer<SoknadSporsmalDTO> {
    private final ObjectMapper objectMapper;

    public SykepengesoknadDeserializer() {
        objectMapper = new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public SoknadSporsmalDTO deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, SoknadSporsmalDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("Feil ved konvertering av bytes til Sykepengesøknad", e);
        }
    }

    @Override
    public void close() {

    }
}
