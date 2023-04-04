package data

import com.google.common.truth.Truth.assertThat
import domain.LineRepository
import domain.RouteRepository
import io.mockk.every
import io.mockk.mockk
import model.Line
import model.Station
import model.StationCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

internal class RouteRepositoryImplTest {
    private lateinit var routeRepository: RouteRepository

    private val lineRepository = mockk<LineRepository>()

    @BeforeEach
    fun setUp() {
        routeRepository = RouteRepositoryImpl(lineRepository)
    }

    @Test
    fun `findRoute return one line that connect the two stations`() {
        val line1 = Line(
            name = "Line 1",
            stations = listOf(
                Station(StationCode("ABC1"),"A"),
                Station(StationCode("ABC2"),"B"),
                Station(StationCode("ABC3"),"C"),
            )
        )

        val line2 = Line(
            name = "Line 2",
            stations = listOf(
                Station(StationCode("ABC4"),"E"),
                Station(StationCode("ABC5"),"F"),
                Station(StationCode("ABC6"),"G"),
            )
        )

        every { lineRepository.getAllLines() } returns LinkedList(listOf(line1, line2))

        val expectedLines = listOf(line1)
        val routes = routeRepository.findRoute("A", "C")

        assertThat(routes).containsExactlyElementsIn(expectedLines)
    }

    @Test
    fun `findRoute return two lines that connect the two stations`() {
        val line1 = Line(
            name = "Line 1",
            stations = listOf(
                Station(StationCode("ABC1"),"A"),
                Station(StationCode("ABC2"),"B"),
                Station(StationCode("ABC3"),"C"),
            )
        )

        val line2 = Line(
            name = "Line 2",
            stations = listOf(
                Station(StationCode("ABC1"),"A"),
                Station(StationCode("ABC4"),"F"),
                Station(StationCode("ABC2"),"B"),
            )
        )

        val line3 = Line(
            name = "Line 2",
            stations = listOf(
                Station(StationCode("ABC1"),"A"),
                Station(StationCode("ABC4"),"F"),
                Station(StationCode("ABC4"),"D"),
            )
        )

        every { lineRepository.getAllLines() } returns LinkedList(listOf(line1, line2, line3))

        val expectedLines = listOf(line1, line2)
        val routes = routeRepository.findRoute("A", "B")

        assertThat(routes).containsExactlyElementsIn(expectedLines)
    }
}