package domain

import model.Line
import model.StationCode
import java.util.LinkedList

interface LineRepository {
    fun addLine(
        lineName: String,
        stationsCodes: List<StationCode>
    )

    fun getAllLines(): LinkedList<Line>
}