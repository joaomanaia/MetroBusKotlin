package domain

import model.Station
import model.StationCode
import core.common.exceptions.StationHasConnectionsException

interface StationRepository {
    /**
     * Get all stations in the repository.
     *
     * @return A list of all stations
     */
    fun getAllStations(): List<Station>

    /**
     * Add a station to the repository with the given name.
     * The station code will be generated automatically.
     *
     * @param name The name of the station
     */
    fun addStation(name: String)

    /**
     * Add multiple stations to the repository with the given names.
     * The station codes will be generated automatically.
     *
     * @param names The names of the stations
     * @see addStation
     */
    fun addStations(vararg names: String) = names.forEach { name -> addStation(name) }

    /**
     * Remove a station from the repository with the given code.
     * If the station does not exist, nothing happens.
     * If the station has any connections, they will need to be removed, then the station can be removed.
     *
     * @param code The code of the station
     * @throws StationHasConnectionsException If the station has any connections
     */
    fun removeStation(code: StationCode)
}