package domain

import model.Line

interface RouteRepository {
    /**
     * Find the routes that connect the two stations.
     * If there are multiple routes, return all of them.
     *
     * @param from The name of the station where the route starts.
     * @param to The name of the station where the route ends.
     * @return The list of routes that connect the two stations.
     */
    fun findRoute(from: String, to: String): List<Line>
}