package model

import view.internalComponents.Number

class NumbersTemplatesService {
    // Properties
    private val templates = listOf(
        template0,
        template1,
        template2,
        template3,
        template4,
        template5,
        template6,
        template7,
        template8,
        template9
    )

    // Getters
    fun getTemplateFor(number: Number): List<Double> {
        return templates[number.int].flatten()
    }

    // Static
    companion object {
        private val template0 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 0, 1, 1, 1, 1, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 1, 1, 1, 1, 0, 0),
            listOf(0, 0, 0, 0, 0, 0, 0, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template1 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 1, 1, 0, 0, 0),
            listOf(0, 0, 0, 0, 0, 0, 0, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template2 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 1, 1, 1, 0),
            listOf(0, 0, 0, 1, 1, 1, 0, 0),
            listOf(0, 0, 1, 1, 1, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template3 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 0, 0, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template4 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 0, 0, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template5 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 0, 0),
            listOf(0, 0, 0, 0, 1, 1, 1, 0),
            listOf(0, 0, 0, 0, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 0, 0),
            listOf(0, 1, 1, 1, 1, 0, 0, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template6 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template7 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 0, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template8 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0)
        ).map { ints -> ints.map { it.toDouble() } }

        private val template9 = listOf(
            listOf(0, 0, 0, 0, 0, 0, 0, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 0, 0, 0, 0, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0),
            listOf(0, 1, 1, 1, 1, 1, 1, 0)
        ).map { ints -> ints.map { it.toDouble() } }
    }
}