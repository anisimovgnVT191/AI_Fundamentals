
import models.Ant
import models.City
import org.jetbrains.letsPlot.Stat
import org.jetbrains.letsPlot.export.ggsave
import org.jetbrains.letsPlot.geom.geomLine
import org.jetbrains.letsPlot.geom.geomPoint
import org.jetbrains.letsPlot.letsPlot
import org.jetbrains.letsPlot.pos.positionIdentity
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val antAlgorithm = AntAlgorithm(
        alpha = 1.0,
        beta = 5.0,
        p = 0.5,
        q = 100,
        maxDistance = 100,
        numberOfCities = 40,
        numberOfAnts = 40
    )

    drawInitialCitiesGraph(antAlgorithm.cities)

    val elapsedTime = measureTimeMillis {
        antAlgorithm.run()
    } / 1000.0

    antAlgorithm.bestAnt?.let { ant ->
        drawBestAntRoute(ant, fileName = "bestAnt")
    }
    antAlgorithm.firstBestAnt?.let { ant ->
        drawBestAntRoute(ant, fileName = "firstBestAnt")
    }
    print(antAlgorithm)
}

fun drawInitialCitiesGraph(cities: List<City>) {
    var fig = letsPlot()
        .plus(
            geomPoint(
                data = cities.pointsData,
                color = "dark-green",
                size = 4.0
            ) { x = "x"; y = "y" })

    val xs = cities.map { it.x }.mapIndexed { index, x -> cities.map { it.x }.drop(index + 1).map { Pair(x, it) } }.flatten()
    val ys = cities.map { it.y }.mapIndexed { index, x -> cities.map { it.y }.drop(index + 1).map { Pair(x, it) } }.flatten()

    xs.zip(ys).forEach { pair ->
        fig += geomLine(data = mapOf(
            "x" to listOf(pair.first.first, pair.first.second),
            "y" to listOf(pair.second.first, pair.second.second))
        ) {x = "x"; y = "y"}
    }

    ggsave(fig, "citiesGraph.png")
}

fun drawBestAntRoute(ant: Ant, fileName: String) {
    val xs = ant.path.map { it.x }
    val ys = ant.path.map { it.y }
    val data = mapOf("x" to xs + xs.first(), "y" to ys + ys.first())

    var fig = letsPlot() + geomPoint(
        data = data,
        color = "dark-green",
        size = 4.0
    ) { x = "x"; y = "y" }

    val lineXs = (xs + xs.first()).zipWithNext()
    val lineYx = (ys + ys.first()).zipWithNext()

    lineXs.zip(lineYx).forEach { pair ->
        fig += geomLine(data = mapOf(
            "x" to listOf(pair.first.first, pair.first.second),
            "y" to listOf(pair.second.first, pair.second.second))
        ) {x = "x"; y = "y"}
    }

    ggsave(fig, "$fileName.png")
}

private val List<City>.pointsData: Map<String, List<Int>>
    get() {
        return mapOf("x" to map { it.x }, "y" to map { it.y })
    }