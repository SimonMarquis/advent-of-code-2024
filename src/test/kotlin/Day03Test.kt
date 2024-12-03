import Resources.readLines
import Resources.readText
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 03")
class Day03Test {

    private val sampleInput = readLines("Day03-sample.txt")
    private val sampleInput2 = readLines("Day03-sample2.txt")
    private val actualInput = readLines("Day03.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 161,
            actual = Day03(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 196826776,
            actual = Day03(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 48,
            actual = Day03(sampleInput2).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 106780429,
            actual = Day03(actualInput).part2(),
        )

    }

}
