package model

/**
 * The line contains the information about the line and the stations.
 *
 * @property name The name of the line.
 * @property stations The stations of the line.
 * @see Station
 */
data class Line(
    val name: String,
    val stations: List<Station>
) {
    override fun toString(): String {
        return "Line $name, stations: $stations"
    }
}
