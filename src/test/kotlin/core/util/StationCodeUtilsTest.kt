package core.util

import com.google.common.truth.Truth.assertThat
import model.StationCode
import model.Station
import org.junit.jupiter.api.Test

internal class StationCodeUtilsTest {
    @Test
    fun `test generateUniqueStationCode, should return an station code, when stations is empty`() {
        val code = generateUniqueStationCode(emptyList())

        assertThat(code.value).hasLength(4)
        assertThat(code.value).matches("[A-Z0-9]+")
    }

    @Test
    fun `test generateUniqueStationCode, should return an unique station code, when stations is not empty`() {
        val initialStations = listOf(Station(StationCode("A2DB"), "Station A"))
        val code = generateUniqueStationCode(initialStations)

        assertThat(code.value).hasLength(4)
        assertThat(code.value).matches("[A-Z0-9]+")
        assertThat(code.value).isNotEqualTo("A2DB")
    }

    @Test
    fun `test generateUniqueStationCode generates unique codes`() {
        val stations = listOf(
            Station(StationCode("AAAA"), "Station 1"),
            Station(StationCode("BBBB"), "Station 2"),
            Station(StationCode("CCCC"), "Station 3")
        )

        val uniqueCode = generateUniqueStationCode(stations)

        assertThat(uniqueCode.value).hasLength(4)
        assertThat(stations.map { it.code.value }).doesNotContain(uniqueCode.value)
    }
}