package view.internalComponents

enum class Number(val int: Int) {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9);

    companion object {
        fun from(int: Int): Number {
            assert(int in 0..9)

            when (int) {
                0 -> return Number.ZERO
                1 -> return Number.ONE
                2 -> return Number.TWO
                3 -> return Number.THREE
                4 -> return Number.FOUR
                5 -> return Number.FIVE
                6 -> return Number.SIX
                7 -> return Number.SEVEN
                8 -> return  Number.EIGHT
                9 -> return Number.NINE
            }
            throw Exception("Number is not in 0..9 range")
        }
    }
}