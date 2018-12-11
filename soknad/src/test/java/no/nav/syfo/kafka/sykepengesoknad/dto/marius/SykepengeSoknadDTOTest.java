package no.nav.syfo.kafka.sykepengesoknad.dto.marius;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SykepengeSoknadDTOTest {

    @Test
    void lagSoknadTest(){
        SykepengeSoknadDTO dto= SykepengeSoknadDTO.builder().build();
        System.out.println(dto);

        assertNotNull(dto);
    }
}
