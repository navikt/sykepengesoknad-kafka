package no.nav.syfo.kafka

import org.apache.kafka.common.header.internals.RecordHeader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets.UTF_8
import java.time.LocalDate
import java.util.Collections.singletonMap

class SyfoProducerRecordTest {
    @Test
    fun noExtraHeaders() {
        val producerRecord = SyfoProducerRecord("topic", "key", "value")

        assertThat(producerRecord.headers()).allMatch { it.key() in listOf(GUID, CALL_ID, NAV_CALLID, TYPE, CREATED_DATE) }

        assertThat(String(producerRecord.headers().lastHeader(GUID).value())).isNotNull()
        assertThat(String(producerRecord.headers().lastHeader(TYPE).value())).isEqualTo("String")
        assertThat(String(producerRecord.headers().lastHeader(CREATED_DATE).value())).startsWith(LocalDate.now().toString())
    }

    @Test
    fun additionalHeaderInConstructor() {
        val producerRecord = SyfoProducerRecord("topic", "key", "value", singletonMap("testHeader", "test"))

        assertThat(producerRecord.headers()).allMatch { it.key() in listOf(GUID, CALL_ID, NAV_CALLID, TYPE, CREATED_DATE, "testHeader") }

        assertThat(producerRecord.headers()).contains(RecordHeader("testHeader", "test".toByteArray(UTF_8)))
    }

    @Test
    fun additionalHeaderPostConstruct() {
        val producerRecord = SyfoProducerRecord("topic", "key", "value")

        producerRecord.addHeaders(singletonMap("testHeader", "test"))

        assertThat(producerRecord.headers()).allMatch { it.key() in listOf(GUID, CALL_ID, NAV_CALLID, TYPE, CREATED_DATE, "testHeader") }

        assertThat(producerRecord.headers()).contains(RecordHeader("testHeader", "test".toByteArray(UTF_8)))
    }
}