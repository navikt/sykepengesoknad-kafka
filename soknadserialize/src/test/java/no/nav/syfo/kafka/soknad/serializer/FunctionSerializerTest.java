package no.nav.syfo.kafka.soknad.serializer;

import no.nav.syfo.kafka.interfaces.Soknad;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FunctionSerializerTest {

    private class Testsoknad implements Soknad {
        public String id;

        public Testsoknad(String id) {
            this.id = id;
        }
    }

    @Test
    void test() {
        String soknadSomString = "id";

        Testsoknad testsoknad = new Testsoknad("id");

        List<Soknad> passedSoknad = new ArrayList<>();

        FunctionSerializer<Testsoknad> serializer = new FunctionSerializer<>(soknad -> {
            passedSoknad.add(soknad);
            return soknad.id.getBytes(UTF_8);
        });

        byte[] bytes = serializer.serialize("topic", testsoknad);

        assertThat(bytes).containsExactly(soknadSomString.getBytes());
        assertThat(passedSoknad).hasSize(1).containsExactly(testsoknad);
    }

    @Test
    void testFeilISerialisererfunksjon() {
        Testsoknad testsoknad = new Testsoknad("id");

        List<Soknad> passedSoknad = new ArrayList<>();

        FunctionSerializer<Testsoknad> serializer = new FunctionSerializer<>(soknad -> {
            passedSoknad.add(soknad);
            throw new IllegalArgumentException();
        });

        assertThatThrownBy(() -> serializer.serialize("topic", testsoknad))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Feil ved konvertering av søknad til bytes");

        assertThat(passedSoknad).hasSize(1).containsExactly(testsoknad);
    }
}

