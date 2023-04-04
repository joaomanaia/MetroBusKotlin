package core.common.exceptions

import model.StationCode

class StationHasConnectionsException(
    private val stationCode: StationCode
) : Exception("Station $stationCode has connections")
