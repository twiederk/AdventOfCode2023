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

    @Test
    fun should_create_damaged_groups_for_1_1_111() {

        // act
        val damagedGroups = Day12().damagedGroup("#.#.###")

        // assert
        assertThat(damagedGroups).containsExactly(1, 1, 3)
    }

    @Test
    fun should_create_damaged_groups_for__1___1____111_() {

        // act
        val damagedGroups = Day12().damagedGroup(".#...#....###.")

        // assert
        assertThat(damagedGroups).containsExactly(1, 1, 3)
    }

    @Test
    fun should_create_damaged_groups_for__1_111_1_111111() {

        // act
        val damagedGroups = Day12().damagedGroup(".#.###.#.######")

        // assert
        assertThat(damagedGroups).containsExactly(1,3,1,6)
    }

    @Test
    fun should_create_damaged_groups_for_1111_1___1___() {

        // act
        val damagedGroups = Day12().damagedGroup("####.#...#...")

        // assert
        assertThat(damagedGroups).containsExactly(4, 1, 1)
    }

    @Test
    fun should_create_damaged_groups_for_1____111111__11111_() {

        // act
        val damagedGroups = Day12().damagedGroup("#....######..#####.")

        // assert
        assertThat(damagedGroups).containsExactly(1, 6, 5)
    }

    @Test
    fun should_create_damaged_groups_for__111_11____1() {

        // act
        val damagedGroups = Day12().damagedGroup(".###.##....#")

        // assert
        assertThat(damagedGroups).containsExactly(3, 2, 1)
    }
}