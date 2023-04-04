package core.util

import model.StationCode
import model.Station

/**
 * Generate an alphanumeric [StationCode] that is not already in use.
 *
 * @param stations The list of stations to check against
 * @return The generated station code
 * @see StationCode
 */
internal fun generateUniqueStationCode(stations: List<Station>): StationCode {
    val letters = ('A'..'Z') + ('0'..'9')

    // Generate a random 4 character string
    val code = letters
        .shuffled()
        .take(4)
        .joinToString("")

    // Get all the station codes
    val stationsCode = stations.map { it.code.value }

    // Check if the code is already in use
    if (code in stationsCode) {
        // If it is, generate a new code
        return generateUniqueStationCode(stations)
    }

    return StationCode(code)
}