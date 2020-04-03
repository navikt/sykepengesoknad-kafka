package no.nav.syfo.kafka.soknad.serializer

import org.junit.jupiter.api.Test

import java.util.ArrayList

import java.nio.charset.StandardCharsets.UTF_8
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy

internal class FunctionSerializerTest {

    private inner class Testsoknad(var id: String)

    @Test
    fun test() {
        val soknadSomString = "id"

        val testsoknad = Testsoknad("id")

        val passedSoknad = ArrayList<Testsoknad>()

        val serializer = FunctionSerializer<Testsoknad> { soknad ->
            passedSoknad.add(soknad)
            soknad.id.toByteArray(UTF_8)
        }

        val bytes = serializer.serialize("topic", testsoknad)

        assertThat(bytes).containsExactly(*soknadSomString.toByteArray())
        assertThat(passedSoknad).hasSize(1).containsExactly(testsoknad)
    }

    @Test
    fun testFeilISerialisererfunksjon() {
        val testsoknad = Testsoknad("id")

        val passedSoknad = ArrayList<Testsoknad>()

        val serializer = FunctionSerializer<Testsoknad> { soknad ->
            passedSoknad.add(soknad)
            throw IllegalArgumentException()
        }

        assertThatThrownBy { serializer.serialize("topic", testsoknad) }
                .isInstanceOf(IllegalArgumentException::class.java)


        assertThat(passedSoknad).hasSize(1).containsExactly(testsoknad)
    }
}

