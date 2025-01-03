import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 09")
class Day09Test {

    private val sampleInput = readLines("Day09-sample.txt")
    private val actualInput = readLines("Day09.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 1928,
            actual = Day09(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 6225730762521,
            actual = Day09(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 2858,
            actual = Day09(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 6250605700557,
            actual = Day09(actualInput).part2(),
        )

    }

}
