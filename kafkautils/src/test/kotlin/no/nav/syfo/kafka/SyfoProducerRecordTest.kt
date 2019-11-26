package no.nav.syfo.kafka

import org.apache.kafka.common.header.internals.RecordHeader
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Fail.fail
import org.junit.jupiter.api.Test
import org.slf4j.MDC
import java.nio.charset.StandardCharsets.UTF_8
import java.time.LocalDate
import java.util.Collections.singletonMap
import java.util.UUID


class SyfoProducerRecordTest {
    @Test
    fun noExtraHeaders() {
        val producerRecord = SyfoProducerRecord("topic", "key", "value")

        assertThat(producerRecord.headers()).allMatch { it.key() in listOf(GUID, CALL_ID, NAV_CALLID, TYPE, CREATED_DATE) }

        assertThat(String(producerRecord.headers().lastHeader(GUID).value())).isNotNull()
        assertThat(String(producerRecord.headers().lastHeader(NAV_CALLID).value())).isNotNull()
        assertThat(String(producerRecord.headers().lastHeader(CALL_ID).value())).isNotNull()
        assertIsUuid(String(producerRecord.headers().lastHeader(GUID).value()));
        assertIsUuid(String(producerRecord.headers().lastHeader(NAV_CALLID).value()));
        assertIsUuid(String(producerRecord.headers().lastHeader(CALL_ID).value()));
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

    @Test
    fun `NavCallId og CallId blir satt fra MDC`() {
        MDC.put(NAV_CALLID, "call-id-123-hey")

        val producerRecord = SyfoProducerRecord("topic", "key", "value")

        assertThat(String(producerRecord.headers().lastHeader(CALL_ID).value())).isEqualTo("call-id-123-hey")
        assertThat(String(producerRecord.headers().lastHeader(NAV_CALLID).value())).isEqualTo("call-id-123-hey")

        MDC.remove(NAV_CALLID)
    }

    @Test
    fun `NavCallId og CallId blir generert hvis callid på MDC er tom streng`() {
        MDC.put(NAV_CALLID, "")

        val producerRecord = SyfoProducerRecord("topic", "key", "value")

        assertIsUuid(String(producerRecord.headers().lastHeader(NAV_CALLID).value()));
        assertIsUuid(String(producerRecord.headers().lastHeader(CALL_ID).value()));

        MDC.remove(NAV_CALLID)
    }

    @Test
    fun `NavCallId og CallId blir generert hvis callid på MDC er blank streng`() {
        MDC.put(NAV_CALLID, " ")

        val producerRecord = SyfoProducerRecord("topic", "key", "value")

        assertIsUuid(String(producerRecord.headers().lastHeader(NAV_CALLID).value()));
        assertIsUuid(String(producerRecord.headers().lastHeader(CALL_ID).value()));

        MDC.remove(NAV_CALLID)
    }
}

fun assertIsUuid(uuid: String) {
    try {
        UUID.fromString(uuid)
    } catch (exception: IllegalArgumentException) {
        fail<String>("Forventet at $uuid er en uuid")
    }
}