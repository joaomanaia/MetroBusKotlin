import data.LineRepositoryImpl
import data.RouteRepositoryImpl
import data.StationRepositoryImpl
import domain.LineRepository
import domain.RouteRepository
import domain.StationRepository
import model.StationCode

enum class UserInputType {
    ADD_STATION,
    ADD_LINE,

    GET_STATIONS,
    GET_LINES,

    FIND_ROUTE
}

fun main() {
    val stationRepository: StationRepository = StationRepositoryImpl()
    val lineRepository: LineRepository = LineRepositoryImpl(stationRepository)
    val routeRepository: RouteRepository = RouteRepositoryImpl(lineRepository)

    // Make the user add the stations, lines and routes.
    // The user input should be a number corresponding to the action.
    while (true) {
        print("""
            Enter the number of the action you want to perform:
            
            1. Add a station
            2. Add a line
            
            3. Get all stations
            4. Get all lines
            
            5. Find a route
            
            Action: 
        """.trimIndent())

        val input = readlnOrNull()?.toIntOrNull() ?: break
        val inputType = UserInputType.values().getOrNull(input - 1) ?: break

        when (inputType) {
            UserInputType.ADD_STATION -> addStation(stationRepository)
            UserInputType.ADD_LINE -> addLine(lineRepository)
            UserInputType.GET_STATIONS -> getStations(stationRepository)
            UserInputType.GET_LINES -> getLines(lineRepository)
            UserInputType.FIND_ROUTE -> findRoute(routeRepository)
        }
    }
}

private fun addStation(stationRepository: StationRepository) {
    print("Enter the name of the station: ")

    val stationName = readlnOrNull() ?: return
    stationRepository.addStation(stationName)

    println("The station was added successfully\n")
}

private fun addLine(lineRepository: LineRepository) {
    print("Enter the name of the line: ")
    val lineName = readlnOrNull() ?: return

    print("Enter the stations codes separated by commas:")
    val stationsCodes = readlnOrNull()
        ?.split(",")
        ?.map { code -> StationCode(code) } ?: return

    lineRepository.addLine(lineName, stationsCodes)

    println("The line was added successfully\n")
}

private fun getStations(stationRepository: StationRepository) {
    val stations = stationRepository.getAllStations()

    println("The stations are: $stations\n")
}

private fun getLines(lineRepository: LineRepository) {
    val lines = lineRepository.getAllLines()

    println("The lines are: $lines\n")
}

private fun findRoute(routeRepository: RouteRepository) {
    print("Enter the name of the station where the route starts: ")
    val from = readlnOrNull() ?: return

    print("Enter the name of the station where the route ends: ")
    val to = readlnOrNull() ?: return

    val routes = routeRepository.findRoute(from, to)

    println("The routes are: $routes\n")
}
