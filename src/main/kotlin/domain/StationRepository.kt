package domain

import model.Station

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
}