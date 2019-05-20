package no.nav.syfo.kafka.soknad.deserializer

import no.nav.syfo.kafka.MELDINGSTYPE
import no.nav.syfo.kafka.getLastHeaderByKeyAsString
import org.apache.kafka.common.header.Headers
import org.apache.kafka.common.serialization.Deserializer


/**
 *
 * [MultiFunctionDeserializer] brukes for å kunne ha flere meldingstyper på samme kafkatopic.
 * Ved å angi ulike mapperfunksjoner i [deserializeMap][MultiFunctionDeserializer.deserializeMap]
 * vil deserializeren velge den mapperfunksjonen som har samme key som
 * [MELDINGSTYPE][no.nav.syfo.kafka.MELDINGSTYPE]-headeren i meldingen.
 *
 *
 * Hvis [MELDINGSTYPE][no.nav.syfo.kafka.MELDINGSTYPE]-headeren ikke er
 * satt i meldingen, eller det ikke finnes en mapperfunksjon med samme key, så brukes
 * [deserializeDefault][MultiFunctionDeserializer.deserializeDefault] i stedet
 *
 *
 * Eksempel som vil mappe meldingen til en String, hvis
 * [MELDINGSTYPE][no.nav.syfo.kafka.MELDINGSTYPE]-headeren == "meldingstype":
 *
 * <tt>MultiFunctionDeserializer(mapOf("meldingstype" to { headers, melding -> String(melding) }))</tt>
 *
 * @param T typen det skal deserialiseres til
 * @param deserializeMap     map med key som matcher
 * [MELDINGSTYPE][no.nav.syfo.kafka.MELDINGSTYPE]-headeren i meldingen
 * @param deserializeDefault default mapperfunksjon som brukes hvis ingen spesifikk blir funnet. Kan være null
 */
class MultiFunctionDeserializer<T>(
        private val deserializeMap: Map<String, (h: Headers?, b: ByteArray?) -> T>,
        private val deserializeDefault: ((b: ByteArray?) -> T)? = null
) : Deserializer<T> {

    override fun configure(configs: Map<String, *>, isKey: Boolean) {}

    /**
     *§
     * Deserialiserer meldingen med den mapperfunksjonen som matcher med
     * [MELDINGSTYPE][no.nav.syfo.kafka.MELDINGSTYPE]-headeren i meldingen.
     *
     *
     * Hvis ingen mapperfunksjon blir funnet, brukes
     * [deserializeDefault][MultiFunctionDeserializer.deserializeDefault] i stedet
     *
     * @param topic   topic som dataene kommer fra
     * @param headers headerne til meldingen. Kan være null
     * @param bytes   serialisert melding. Kan være null
     * @return deserialisert melding. Kan være null
     * @throws IllegalArgumentException hvis meldingen har en ukjent header-verdi
     * og [deserializeDefault][MultiFunctionDeserializer.deserializeDefault] == null
     */
    override fun deserialize(topic: String, headers: Headers?, bytes: ByteArray?): T {
        val biFunction = getLastHeaderByKeyAsString(headers, MELDINGSTYPE)
                ?.let { deserializeMap[it] }
                ?: return deserialize(topic, bytes)
        return biFunction(headers, bytes)
    }

    /**
     *
     * Deserialiserer meldingen med [deserializeDefault][MultiFunctionDeserializer.deserializeDefault]
     *
     * @param topic topic som dataene kommer fra
     * @param bytes serialisert melding. Kan være null
     * @return deserialisert melding. Kan være null
     * @throws IllegalArgumentException hvis [deserializeDefault][MultiFunctionDeserializer.deserializeDefault] er null
     */
    override fun deserialize(topic: String, bytes: ByteArray?): T {
        val deserialize = deserializeDefault
                ?: throw IllegalArgumentException("Default mapperfunksjon er ikke definert")
        return deserialize(bytes)
    }

    override fun close() {

    }
}
