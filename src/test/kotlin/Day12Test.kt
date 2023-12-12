import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day12Test {

    @Test
    fun should_load_condition_records() {
        // arrange

        // act
        val conditionRecords = Day12().loadConditionRecords(Paths.get("src", "test", "resources", "Day12_TestData.txt"))

        // assert
        assertThat(conditionRecords).hasSize(6)
        assertThat(conditionRecords).containsExactly(
            ConditionRecord("???.###", listOf(1, 1, 3)),
            ConditionRecord(".??..??...?##.", listOf(1, 1, 3)),
            ConditionRecord("?#?#?#?#?#?#?#?", listOf(1, 3, 1, 6)),
            ConditionRecord("????.#...#...", listOf(4, 1, 1)),
            ConditionRecord("????.######..#####.", listOf(1, 6, 5)),
            ConditionRecord("?###????????", listOf(3, 2, 1)),
        )
    }

}