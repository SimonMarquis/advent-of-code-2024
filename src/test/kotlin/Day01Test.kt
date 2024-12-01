import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 01")
class Day01Test {

    private val sampleInput = readLines("Day01-sample.txt")
    private val actualInput = readLines("Day01.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 11,
            actual = Day01(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 3574690,
            actual = Day01(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 31,
            actual = Day01(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 22565391,
            actual = Day01(actualInput).part2(),
        )

    }

}
