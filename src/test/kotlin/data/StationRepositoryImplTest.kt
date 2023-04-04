package data

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class StationRepositoryImplTest {
    private lateinit var stationRepository: StationRepositoryImpl

    @BeforeEach
    fun setUp() {
        stationRepository = StationRepositoryImpl()
    }

    @Test
    fun `getAllStations returns empty list when no stations added`() {
        val stations = stationRepository.getAllStations()

        assertThat(stations).isEmpty()
    }

    @Test
    fun `getAllStations returns all stations that have been added`() {
        stationRepository.addStation("Station 1")
        stationRepository.addStation("Station 2")

        val stations = stationRepository.getAllStations()

        assertThat(stations).hasSize(2)

        assertThat(stations[0].name).isEqualTo("Station 1")
        assertThat(stations[1].name).isEqualTo("Station 2")

        assertThat(stations).containsNoDuplicates()

        // Check unique station name and codes
        assertThat(stations[0].name).isNotEqualTo(stations[1].name)
        assertThat(stations[0].code).isNotEqualTo(stations[1].code)
    }

    @Test
    fun `addStation throw IllegalArgumentException when name already exists`() {
        stationRepository.addStation("Station 1")

        val exception = assertThrows<IllegalArgumentException> {
            stationRepository.addStation("Station 1")
        }

        assertThat(exception.message).isEqualTo("Station with name Station 1 already exists")
    }
}