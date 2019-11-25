package no.nav.syfo.kafka

import org.apache.kafka.common.header.internals.RecordHeaders
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.charset.StandardCharsets

internal class KafkaHeaderUtilsKtTest {

    @Test
    fun `test getLastHeaderByKeyAsString returnerer string`() {
        val headers = RecordHeaders().add("Key", "Min value".toByteArray(StandardCharsets.UTF_8))

        assertThat(getLastHeaderByKeyAsString(headers, "Key")).isEqualTo("Min value")
    }

    @Test
    fun `test getLastHeaderByKeyAsString returnerer null`() {
        val headers = RecordHeaders().add("Key", "Min value".toByteArray(StandardCharsets.UTF_8))

        assertThat(getLastHeaderByKeyAsString(headers, "Annen Key")).isNull()
    }

    @Test
    fun `test getSafeNavCallIdHeaderAsString returnerer string`() {
        val headers = RecordHeaders().add(NAV_CALLID, "Min value".toByteArray(StandardCharsets.UTF_8))

        assertThat(getSafeNavCallIdHeaderAsString(headers)).isEqualTo("Min value")
    }

    @Test
    fun `test getSafeNavCallIdHeaderAsString genererer uuid`() {
        val headers = RecordHeaders()

        assertIsUuid(getSafeNavCallIdHeaderAsString(headers))
    }
}