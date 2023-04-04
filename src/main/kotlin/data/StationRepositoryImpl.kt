package data

import core.util.generateUniqueStationCode
import domain.StationRepository
import model.Station

class StationRepositoryImpl : StationRepository {
    private val stations = mutableListOf<Station>()

    override fun getAllStations(): List<Station> = stations.toList()

    override fun addStation(name: String) {
        // Check is station with name already exists
        if (stations.any { it.name == name }) {
            throw IllegalArgumentException("Station with name $name already exists")
        }

        val code = generateUniqueStationCode(stations)
        val station = Station(code, name)

        stations.add(station)
    }
}
