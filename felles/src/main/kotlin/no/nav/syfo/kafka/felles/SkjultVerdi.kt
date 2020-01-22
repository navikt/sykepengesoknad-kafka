package no.nav.syfo.kafka.felles

class SkjultVerdi <T>(val verdi: T) {
    override fun toString() = "███████████"
}


fun <T> skapSkjultVerdi(verdi: T?): SkjultVerdi<T>? {
    verdi?.let {
        return SkjultVerdi(it)
    }
    return null
}
