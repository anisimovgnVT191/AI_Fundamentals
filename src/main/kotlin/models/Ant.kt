package models

data class Ant(
    var currentCity: City,
    var nextCity: City,
    val visitedCities: MutableList<City>,
    var pathIndex: Int,
    val path: MutableList<City>,
    var tourLength: Double
)
