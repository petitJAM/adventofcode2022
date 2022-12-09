fun main() {

    fun part1(input: List<String>): Int {
        return input.sumOf { inputLine ->
            inputLine.split(" ").let {
                val opponent = RPS.fromInput(it[0])
                val me = RPS.fromInput(it[1])
                scoreGame(me = me, opponent = opponent)
            }
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { inputLine ->
            inputLine.split(" ").let {
                val opponent = RPS.fromInput(it[0])
                val desiredOutcome = DesiredOutcome.fromInput(it[1])
                val iShouldPlay = whatShouldIPlay(opponent, desiredOutcome)
                scoreGame(iShouldPlay, opponent)
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}

private fun scoreGame(me: RPS, opponent: RPS): Int {
    return when (me) {
        RPS.ROCK -> {
            1 + when (opponent) {
                RPS.ROCK -> 3
                RPS.PAPER -> 0
                RPS.SCISSORS -> 6
            }
        }

        RPS.PAPER -> {
            2 + when (opponent) {
                RPS.ROCK -> 6
                RPS.PAPER -> 3
                RPS.SCISSORS -> 0
            }
        }

        RPS.SCISSORS -> {
            3 + when (opponent) {
                RPS.ROCK -> 0
                RPS.PAPER -> 6
                RPS.SCISSORS -> 3
            }
        }
    }
}

private fun whatShouldIPlay(opponent: RPS, desiredOutcome: DesiredOutcome): RPS {
    return when (opponent) {
        RPS.ROCK -> when (desiredOutcome) {
            DesiredOutcome.WIN -> RPS.PAPER
            DesiredOutcome.LOSE -> RPS.SCISSORS
            DesiredOutcome.DRAW -> RPS.ROCK
        }

        RPS.PAPER -> when (desiredOutcome) {
            DesiredOutcome.WIN -> RPS.SCISSORS
            DesiredOutcome.LOSE -> RPS.ROCK
            DesiredOutcome.DRAW -> RPS.PAPER
        }

        RPS.SCISSORS -> when (desiredOutcome) {
            DesiredOutcome.WIN -> RPS.ROCK
            DesiredOutcome.LOSE -> RPS.PAPER
            DesiredOutcome.DRAW -> RPS.SCISSORS
        }
    }
}

private enum class DesiredOutcome {
    WIN,
    LOSE,
    DRAW;

    companion object {
        fun fromInput(inputStr: String): DesiredOutcome = when (inputStr) {
            "X" -> LOSE
            "Y" -> DRAW
            "Z" -> WIN
            else -> throw IllegalArgumentException("Unknown input $inputStr")
        }
    }
}

private enum class RPS {
    ROCK,
    PAPER,
    SCISSORS;

    companion object {
        fun fromInput(inputStr: String): RPS = when (inputStr) {
            "A", "X" -> ROCK
            "B", "Y" -> PAPER
            "C", "Z" -> SCISSORS
            else -> throw IllegalArgumentException("Unknown input string $inputStr")
        }
    }
}
