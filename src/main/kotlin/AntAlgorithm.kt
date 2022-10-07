import models.Ant
import models.City
import utils.forEachWhere
import java.lang.Exception
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.random.Random

data class AntAlgorithm(
    val alpha: Double, // Количество ферментов на пути
    val beta: Double, // Относительная значимость видимости
    val p: Double, // Коэффициент количества фермента
    val q: Int, // Константа, относится к количеству фермента, отсавленному на пути
    val maxDistance: Int,
    val numberOfCities: Int,
    val numberOfAnts: Int
) {
    // Constants
    private val maxTour: Int = maxDistance * numberOfCities
    private val maxSteps: Int = 10000
    private val initPheromone: Double = 1.0 / numberOfCities

    // Properties
    var best: Double = maxTour.toDouble()
    var bestAnt: Ant? = null
    var firstBestAnt: Ant? = null

    val distance: MutableList<MutableList<Double>> = MutableList(numberOfCities) {
        MutableList(numberOfCities) { 0.0 }
    }
    private val pheromone: MutableList<MutableList<Double>> = MutableList(numberOfCities) {
        MutableList(numberOfCities) { initPheromone }
    }

    val cities: List<City> = List(numberOfCities) {
        City(
            x = Random.nextInt(from = 0, until = maxDistance),
            y = Random.nextInt(from = 0, until = maxDistance)
        )
    }
    private val ants: MutableList<Ant>

    init {
        distance.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { elementIndex, element->
                if (rowIndex != elementIndex && element == 0.0) {
                    val xd = abs(cities[rowIndex].x - cities[elementIndex].x)
                    val yd = abs(cities[rowIndex].y - cities[elementIndex].y)

                    distance[rowIndex][elementIndex] = sqrt(xd.toDouble().pow(2) + yd.toDouble().pow(2))
                    distance[elementIndex][rowIndex] = distance[rowIndex][elementIndex]
                }
            }
        }

        var cityIndex: Int = -1
        ants = MutableList(numberOfAnts) {
            cityIndex++
            if (cityIndex == numberOfCities) { cityIndex = 0 }

            Ant(
                currentCity = cities[cityIndex],
                nextCity = cities[cityIndex],
                visitedCities = mutableListOf(cities[cityIndex]),
                pathIndex = 1,
                path = mutableListOf(cities[cityIndex]),
                tourLength = 0.0
            )
        }
    }

    fun run() {
        (1..maxSteps).forEach { step ->
            if (simulateAnts().not()) {
                updateTrails()

                if (step != maxSteps) { reloadAnts() }

                println("Current step = ${step}")
            }
        }
    }

    // Private
    private fun simulateAnts(): Boolean {
        var moving = false

        ants.forEachWhere(condition = { it.pathIndex < numberOfCities} ) { ant ->
            ant.nextCity = ant.selectNextCity()
            ant.visitedCities.add(ant.nextCity)

            ant.path.add(ant.nextCity)
            ant.pathIndex++

            ant.tourLength += distance[ant.currentCity.index][ant.nextCity.index]

            if (ant.pathIndex == numberOfCities) {
                ant.tourLength += distance[ant.path.last().index][ant.path.first().index]
            }

            ant.currentCity = ant.nextCity

            moving = true
        }

        return moving
    }

    private fun reloadAnts() {
        var cityIndex = 0

        ants.forEachIndexed { antIndex, ant ->
            if (ant.tourLength < best) {
                if (firstBestAnt == null) { firstBestAnt = ant.copy() }
                best = ant.tourLength
                bestAnt = ant.copy()
            }

            if (cityIndex == numberOfCities) { cityIndex = 0 }

            ants[antIndex] = Ant(
                currentCity = cities[cityIndex],
                nextCity = cities[cityIndex],
                visitedCities = mutableListOf(cities[cityIndex]),
                path = mutableListOf(cities[cityIndex]),
                pathIndex = 1,
                tourLength = 0.0
            )
            cityIndex++
        }
    }

    private fun updateTrails() {
        // Испарение фермента
        pheromone.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { elementIndex, _ ->
                if (rowIndex != elementIndex) {
                    pheromone[rowIndex][elementIndex] *= (1.0 - p)
                    if (pheromone[rowIndex][elementIndex] < 0.01) {
                        pheromone[rowIndex][elementIndex] = initPheromone
                    }
                }
            }
        }

        // Нанесение новго фермента
        ants.forEach { ant ->
            for (i in cities.indices) {
                val (from, to) = if (i < numberOfCities - 1) {
                    Pair(ant.path[i].index, ant.path[i+1].index)
                } else {
                    Pair(ant.path.last().index, ant.path.first().index)
                }

                pheromone[from][to] += (q.toDouble() / ant.tourLength)
                pheromone[to][from] = pheromone[from][to]
            }
        }

        pheromone.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { elemnetIndex, _ ->
                pheromone[rowIndex][elemnetIndex] *= p
            }
        }
    }

    override fun toString(): String {
        return """Матрица дистанции
            |${distance.joinToString(separator = "\n")}
            |Лучший путь:
            |${bestAnt?.path}
        """.trimMargin()
    }

    // Extensions
    private fun Ant.selectNextCity(): City {
        var denom = 0.0
        val availableCities = cities.toSet().minus(visitedCities.toSet()).toList()

        availableCities.forEach { city ->
            denom += product(destination = city)
        }

        do {
            availableCities.forEach { city ->
                val p = product(destination = city) / denom
                if (Random.nextDouble(from = 0.0, until = 1.0) < p) { return city }
            }
        } while (true)
    }

    private fun Ant.product(destination: City): Double {
        val leftSide = pheromone[currentCity.index][destination.index].pow(alpha)
        val rightSide = (1.0 / distance[currentCity.index][destination.index]).pow(beta)

        return leftSide * rightSide
    }

    private val City.index: Int
        get() { return cities.indexOf(this) }
}
