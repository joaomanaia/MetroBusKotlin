package data

import domain.LineRepository
import domain.RouteRepository
import model.Line

class RouteRepositoryImpl(
    private val lineRepository: LineRepository
) : RouteRepository {
    override fun findRoute(from: String, to: String): List<Line> {
        return lineRepository
            .getAllLines()
            .filter { line ->
                val lineStations = line.stations.map { it.name }

                from in lineStations && to in lineStations
            }
    }
}
