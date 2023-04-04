package data

import core.util.generateUniqueStationCode
import domain.StationRepository
import model.Station
import model.StationCode

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

    override fun removeStation(code: StationCode) {
        // Check if station exists
        val station = stations.find { it.code == code }
            ?: throw IllegalArgumentException("Station with code $code does not exist")

        // TODO: Check if station has connections

        stations.remove(station)
    }
}
