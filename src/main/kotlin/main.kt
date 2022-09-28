import kotlin.random.Random

fun main () {
    val art1 = Art1(
        beta = 1,
        vigilance = 0.9,
        d = 11,
        prototypes = mutableListOf(),
        attributes = listOf(
            listOf(0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0).map { it.toBoolean() },
            listOf(0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1).map { it.toBoolean() },
            listOf(0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0).map { it.toBoolean() },
            listOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1).map { it.toBoolean() },
            listOf(1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0).map { it.toBoolean() },
            listOf(1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0).map { it.toBoolean() },
            listOf(1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0).map { it.toBoolean() },
            listOf(0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0).map { it.toBoolean() },
            listOf(0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0).map { it.toBoolean() },
            listOf(0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0).map { it.toBoolean() }
        )
    )

    art1.performArt1()

    println(art1)
}

private fun Int.toBoolean(): Boolean {
    return this > 0
}