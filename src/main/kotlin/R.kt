object R {
    object NQueensParameterNames {
        val N = "N"

        val initialTemperature = "Начальная температура"

        val finalTemperature = "Конечная температура"

        val alpha = "Альфа"

        val stepsPerChange = "Шагов до понижения"
    }

    object ButtonsTitle {
        val findSolution = "Найти оптимальное решение"

        val showCharts = "Показать графики"
    }

    object Statistic {
        fun elapsedTime(time: String) = "Затраченное время: $time"

        fun bestEnergySolution(energy: String) = "Энергия лучшего решения: $energy"

        fun acceptedSolution(count: String) = "Количество принятых решений: $count"
    }
}