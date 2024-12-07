import kotlin.math.abs

fun main() {
    fun part1(left: List<Int>, right: List<Int>): Int {
        return left.sorted()
            .zip(right.sorted())
            .map { (left, right) -> abs(left - right) }
            .reduce { a, b -> a + b }
    }

    fun part2(left: List<Int>, right: List<Int>): Int {
        val frequencies = right.groupingBy { it }.eachCount()

        return left
            .map { frequencies.getOrDefault(it, 0) * it }
            .sum()
    }

    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `data/Day01_test.txt` file:
    val testLines = readInput("Day01_test")
    val (testLeft, testRight) = testLines.map { line ->
        line.split("""\s+""".toRegex()).let { it[0].toInt() to it[1].toInt()}
    }.unzip()
    check(part1(testLeft, testRight) == 11)
    check(part2(testLeft, testRight) == 31)

    // Read the input from the `data/Day01.txt` file.
    val input = readInput("Day01")
    val (left, right) = input.map { line ->
        line.split("""\s+""".toRegex()).let { it[0].toInt() to it[1].toInt()}
    }.unzip()
    part1(left, right).println()
    part2(left, right).println()
}
