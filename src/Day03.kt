fun main() {
    fun part1(input: List<String>): Long {
        val regex = """mul\((\d+),(\d+)\)""".toRegex()
        return input.map { line ->
            regex.findAll(line)
                .map { it.groupValues[1].toLong() * it.groupValues[2].toLong() }
                .sum()
        }.sum()
    }

    fun prepareString(line: String): String {
        var result = ""
        var disabled: String
        var enabledIndex = 0
        var disableIndex = line.indexOf("don't()")
        var enabled = line
        while (disableIndex != -1) {
            result += enabled.substring(0, disableIndex)
            disabled = enabled.substring(disableIndex + 1)
            enabledIndex = disabled.indexOf("do()")
            enabled = disabled.substring(enabledIndex + 1)
            disableIndex = enabled.indexOf("don't()")
        }
        return result + enabled
    }

    fun part2(input: List<String>): Long {
        val oneLine = input.fold("") { a, b -> a + b }
        val prepared = prepareString(oneLine)
        return part1(listOf(prepared))
    }

    // Test if implementation meets criteria from the description, like:
    check(part1(listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")) == 161L)
    check(part2(listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")) == 48L)

    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
//    input.println()
    part1(input).println()
    part2(input).println()
}
