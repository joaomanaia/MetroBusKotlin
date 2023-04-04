package model

/**
 * Station code is a 4 character alphanumeric string.
 * @param value The station code
 * @throws IllegalArgumentException if the station code is not 4 characters long or contains non-alphanumeric characters
 */
@JvmInline
value class StationCode(val value: String) {
    init {
        // Check that the code is 4 characters long
        require(value.length == 4) { "Station code must be 4 characters long" }

        // Check that all characters are alphanumeric
        val isAlphaNumeric = value.all(Char::isLetterOrDigit)
        require(isAlphaNumeric) { "Station code must be alphanumeric" }
    }

    override fun toString(): String = value
}

/**
 * The data class of stop station.
 *
 * @param code The station code
 * @param name The station name
 *
 * @see StationCode
 */
data class Station(
    val code: StationCode,
    val name: String
) {
    override fun toString(): String = "$code - $name"
}
