data class Art1(
    val beta: Int, // Небольшое целое число
    val vigilance: Double,  // Внимательность < 1
    val d: Int, // Длина
    val prototypes: MutableList<List<Boolean>>,
    val attributes: List<List<Boolean>>
) {
    private val membership = MutableList(d) { -1 }

    fun performArt1() {
        var shouldBreak = false
        if (prototypes.isEmpty()) { prototypes.add(attributes.first()) }

        while (shouldBreak.not()) {
            shouldBreak = true
            attributes.forEachIndexed { indexAttr, attribute ->
                prototypes.forEachIndexed { indexPrt, prototype ->
                    if (prototype.areSimilarTo(attribute) && prototype.vigilanceTest(attribute)) {
                        membership[indexAttr] = indexPrt
                        prototypes[indexPrt] = prototype.intersect(attribute)
                    }
                }
                if (membership[indexAttr] == -1) {
                    prototypes.add(attribute)
                    membership[indexAttr] = prototypes.count()
                    shouldBreak = false
                }
             }
        }
    }

    private fun List<Boolean>.areSimilarTo(right: List<Boolean>): Boolean {
        val leftSide = this.intersect(right).magnitude().toDouble() / (beta + this.magnitude())
        val rightSide = right.magnitude().toDouble() / (beta + d)

        return leftSide > rightSide
    }

    private fun List<Boolean>.vigilanceTest(right: List<Boolean>): Boolean {
        val leftSide = this.intersect(right).magnitude().toDouble() / right.magnitude()

        return leftSide < vigilance
    }

    private fun List<Boolean>.magnitude(): Int {
        return  this.fold(initial = 0) { acc, bool -> acc + bool.toInt()}
    }

    private fun List<Boolean>.intersect(right: List<Boolean>): List<Boolean> {
        return this.zip(right).map { it.first && it.second }
    }

    private fun Boolean.toInt(): Int {
        return if (this) return 1 else 0
    }

    override fun toString(): String {
        return membership.toString()
    }
}