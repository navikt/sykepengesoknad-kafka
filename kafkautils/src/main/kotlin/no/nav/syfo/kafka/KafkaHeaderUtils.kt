package no.nav.syfo.kafka

import org.apache.kafka.common.header.Headers
import java.nio.charset.StandardCharsets.UTF_8
import java.util.*


fun getLastHeaderByKeyAsString(headers: Headers?, key: String): String? =
        headers
                ?.lastHeader(key)
                ?.value()
                ?.let { String(it, UTF_8) }

fun getSafeNavCallIdHeaderAsString(headers: Headers): String =
    sequenceOf(getLastHeaderByKeyAsString(headers, NAV_CALLID))
            .filterNotNull()
            .filter { it.isNotBlank() }
            .filter { it.isNotEmpty() }
            .firstOrNull() ?: UUID.randomUUID().toString()

