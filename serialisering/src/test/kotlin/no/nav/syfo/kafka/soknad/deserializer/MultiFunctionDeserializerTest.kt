package no.nav.syfo.kafka.soknad.deserializer

import no.nav.syfo.kafka.MELDINGSTYPE
import org.apache.kafka.common.header.Header
import org.apache.kafka.common.header.internals.RecordHeader
import org.apache.kafka.common.header.internals.RecordHeaders
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets.UTF_8
import java.util.Collections.emptyMap

class MultiFunctionDeserializerTest {

    private inner class Testsoknad(var id: String)

    @Test
    fun testDefault() {
        val deserializer = MultiFunctionDeserializer(emptyMap()) { bytes ->
            bytes?.let { Testsoknad(String(it, UTF_8)) }
        }

        val testsoknad = deserializer.deserialize("topic",
                RecordHeaders(listOf<Header>(RecordHeader(MELDINGSTYPE, "spesifikk".toByteArray(UTF_8)))), "id".toByteArray())

        assertThat(testsoknad?.id).isEqualTo("id")
    }

    @Test
    fun testIngenDefault() {
        val deserializer = MultiFunctionDeserializer<Testsoknad>(emptyMap())

        assertThatThrownBy {
            deserializer.deserialize("topic",
                    RecordHeaders(listOf<Header>(RecordHeader(MELDINGSTYPE, "spesifikk".toByteArray(UTF_8)))), "id".toByteArray())
        }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("Default mapperfunksjon er ikke definert")
                .hasNoCause()
    }

    @Test
    fun testFeilIDefault() {
        val deserializer = MultiFunctionDeserializer<Testsoknad>(emptyMap()) { throw IllegalArgumentException() }

        assertThatThrownBy {
            deserializer.deserialize("topic",
                    RecordHeaders(listOf<Header>(RecordHeader(MELDINGSTYPE, "spesifikk".toByteArray(UTF_8)))), "id".toByteArray())
        }
                .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun testSpesifikk() {
        val deserializer = MultiFunctionDeserializer<Testsoknad?>(mapOf("spesifikk" to { _, bytes -> bytes?.let { Testsoknad(String(it, UTF_8)) } }))

        val testsoknad = deserializer.deserialize("topic",
                RecordHeaders(listOf<Header>(RecordHeader(MELDINGSTYPE, "spesifikk".toByteArray(UTF_8)))), "id".toByteArray())

        assertThat(testsoknad!!.id).isEqualTo("id")
    }

    @Test
    fun testFeilISpesifikk() {
        val deserializer = MultiFunctionDeserializer<Testsoknad>(mapOf("spesifikk" to { _, _ -> throw IllegalArgumentException() }))

        assertThatThrownBy {
            deserializer.deserialize("topic",
                    RecordHeaders(listOf<Header>(RecordHeader(MELDINGSTYPE, "spesifikk".toByteArray(UTF_8)))), "id".toByteArray())
        }
                .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun testSpesifikkMatcherIkkeBrukerDefault() {
        val deserializer = MultiFunctionDeserializer(mapOf("ikkeRiktigFunksjon" to
                { _, _ -> Testsoknad("ikkeDenne") })
        ) { bytes -> bytes?.let { Testsoknad(String(it, UTF_8)) } }

        val testsoknad = deserializer.deserialize("topic",
                RecordHeaders(listOf<Header>(RecordHeader(MELDINGSTYPE, "spesifikk".toByteArray(UTF_8)))), "id".toByteArray())

        assertThat(testsoknad!!.id).isEqualTo("id")
    }
}
