fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map(String::toIntOrNull)
            .fold(emptyList<List<Int>>()) { acc, i ->
                if (i == null) {
                    acc + listOf(emptyList())
                } else {
                    acc.dropLast(1) + listOf((acc.lastOrNull() ?: emptyList()) + i)
                }
            }
            .maxOfOrNull { it.sum() }
            ?: -1
    }

    fun part2(input: List<String>): Int {
        return input
            .asSequence()
            .map(String::toIntOrNull)
            .fold(emptyList<List<Int>>()) { acc, i ->
                if (i == null) {
                    acc + listOf(emptyList())
                } else {
                    acc.dropLast(1) + listOf((acc.lastOrNull() ?: emptyList()) + i)
                }
            }
            .sortedByDescending(List<Int>::sum)
            .take(3)
            .sumOf(List<Int>::sum)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24_000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
