package no.nav.syfo.kafka.soknad.deserializer

import org.apache.kafka.common.serialization.Deserializer


class FunctionDeserializer<T>(private val deserializer: ((b: ByteArray?) -> T)) : Deserializer<T> {

    override fun configure(configs: Map<String, *>, isKey: Boolean) {}

    override fun deserialize(topic: String?, bytes: ByteArray?): T {
        return deserializer(bytes)
    }


    override fun close() {}
}
