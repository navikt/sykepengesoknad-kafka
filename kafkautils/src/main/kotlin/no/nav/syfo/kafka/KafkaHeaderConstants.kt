package no.nav.syfo.kafka

import org.apache.kafka.common.header.Headers
import java.nio.charset.StandardCharsets.UTF_8

const val GUID = "guid"
const val TYPE = "type"
const val CREATED_DATE = "createdDate"
const val CALL_ID = "callId"
const val NAV_CALLID = "Nav-Callid"
const val MELDINGSTYPE = "MELDINGSTYPE"

fun getLastHeaderByKeyAsString(headers: Headers?, key: String): String?  =
        headers
                ?.lastHeader(key)
                ?.value()
                ?.let { String(it, UTF_8) }
