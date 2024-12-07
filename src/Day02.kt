import kotlin.math.abs

fun main() {

    fun isReportSafe(numbers: List<Int>): Boolean {
        val differences = numbers.zipWithNext { a, b -> a - b }
        return differences.all { it in -3..3 } &&
                (differences.all { it > 0 } || differences.all { it < 0 })
    }

    fun part1Alternative(input: List<String>): Int {
        val reports = input.map { line ->
            line.split(" ").map { it.toInt() }
        }
        return reports.filter { isReportSafe(it) }.size
    }

    fun part1(input: List<String>): Int {
        return input.map { line ->
            line.split(" ")
                .map { it.toInt() }
                .zipWithNext()
        }
            .filter { line -> line.all { abs(it.first - it.second) in 1..3 } }
            .map { line ->
                line.map { if (it.first < it.second) -1 else 1 }
                    .zipWithNext()
            }
            .filter { line -> line.all { it.first == it.second } }
            .size
    }

    fun isDampenedReportSafe(report: List<Int>): Boolean {
        return report.indices.any {
            val skipped = report.toMutableList().apply { removeAt(it) }
            isReportSafe(skipped)
        }
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { line ->
            line.split(" ").map { it.toInt() }
        }
        return reports.filter { isReportSafe(it) || isDampenedReportSafe(it) }.size
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("7 6 4 2 1", "1 2 7 8 9", "1 3 2 4 5")) == 1)
    check(part1Alternative(listOf("7 6 4 2 1", "1 2 7 8 9", "1 3 2 4 5")) == 1)
    check(part2(listOf("7 6 4 2 1", "1 2 7 8 9", "1 3 2 4 5")) == 2)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    val input = readInput("Day02")
//    println(input)
    part1(input).println()
    part2(input).println()
}
