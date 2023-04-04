package data

import com.google.common.truth.Truth.assertThat
import domain.LineRepository
import domain.StationRepository
import io.mockk.every
import io.mockk.mockk
import model.Station
import model.StationCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LineRepositoryImplTest {
    private lateinit var lineRepository: LineRepository

    private val stationRepository = mockk<StationRepository>()

    @BeforeEach
    fun setUp() {
        lineRepository = LineRepositoryImpl(stationRepository)
    }

    @Test
    fun `addLine should add a new line`() {
        val station1 = Station(StationCode("ABC1"), "Station 1")
        val station2 = Station(StationCode("ABC2"), "Station 2")
        every { stationRepository.getAllStations() } returns listOf(station1, station2)

        lineRepository.addLine("Line 1", listOf(StationCode("ABC1"), StationCode("ABC2")))

        assertThat(lineRepository.getAllLines()).hasSize(1)
        assertThat(lineRepository.getAllLines().first.name).isEqualTo("Line 1")
        assertThat(lineRepository.getAllLines().first.stations).containsExactly(station1, station2)
    }

    @Test
    fun `addLine should throw an exception when the line already exists`() {
        every { stationRepository.getAllStations() } returns emptyList()
        lineRepository.addLine("Line 1", emptyList())

        val exception = assertThrows<IllegalArgumentException> {
            lineRepository.addLine("Line 1", emptyList())
        }

        assertThat(exception).hasMessageThat().isEqualTo("The line with name Line 1 already exists")
    }

    @Test
    fun `addLine should throw an exception when the line contains invalid stations`() {
        val station1 = Station(StationCode("ABC1"), "Station 1")
        every { stationRepository.getAllStations() } returns listOf(station1)

        val exception = assertThrows<IllegalArgumentException> {
            lineRepository.addLine("Line 1", listOf(StationCode("ABC1"), StationCode("ABC2")))
        }

        assertThat(exception).hasMessageThat().isEqualTo("The station with code ABC2 does not exist")
    }
}