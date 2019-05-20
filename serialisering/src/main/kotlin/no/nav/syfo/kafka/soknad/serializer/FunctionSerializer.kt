package no.nav.syfo.kafka.soknad.serializer

import org.apache.kafka.common.serialization.Serializer

/**
 *
 * [FunctionSerializer] brukes til å serialisere en melding
 *
 *
 * Når meldingen skal serialiseres til <tt>byte[]</tt>, så kalles [FunctionSerializer.serializer]
 * med meldingen som input
 *
 *
 * Eksempel som vil serialisere meldingen til <tt>ByteArray</tt>:
 *
 * `FunctionSerializer<String> { melding -> melding.toByteArray() }`
 *
 * @param T typen det skal serialiseres fra
 */
class FunctionSerializer<T>(private val serializer: (t: T) -> ByteArray?) : Serializer<T> {

    override fun configure(configs: Map<String, *>, isKey: Boolean) {}

    /**
     *
     * Serialiserer meldingen med [FunctionSerializer.serializer]
     *
     * @param topic topic som dataene skal til
     * @param t     melding som skal serialiseres
     * @return serialisert melding
     */
    override fun serialize(topic: String, t: T): ByteArray? {
        return serializer(t)
    }

    override fun close() {}
}
