package data

import domain.LineRepository
import domain.StationRepository
import model.Line
import model.Station
import model.StationCode
import java.util.LinkedList

class LineRepositoryImpl(
    private val stationRepository: StationRepository
) : LineRepository {
    private val lines = LinkedList<Line>()

    override fun addLine(lineName: String, stationsCodes: List<StationCode>) {
        // Check if the name is already exists.
        if (lines.any { it.name == lineName }) {
            throw IllegalArgumentException("The line with name $lineName already exists")
        }

        // Check if the stations are valid.
        val allStations = stationRepository.getAllStations()

        // Get the stations by their codes.
        val lineStations = stationsCodes.map { stationCode ->
            allStations.find {
                it.code == stationCode
            } ?: throw IllegalArgumentException("The station with code $stationCode does not exist")
        }

        // Add the line.
        val line = Line(lineName, lineStations)
        lines.add(line)
    }

    override fun getAllLines(): LinkedList<Line> = lines
}