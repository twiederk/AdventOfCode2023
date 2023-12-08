import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day08Test {

    @Test
    fun should_load_instructions() {

        // act
        val instructions = Day08().loadInstructions(Paths.get("src", "test", "resources", "Day08_TestData.txt"))

        // assert
        assertThat(instructions).isEqualTo("RL")
    }

    @Test
    fun should_load_nodes() {

        // act
        val nodes = Day08().loadNodes(Paths.get("src", "test", "resources", "Day08_TestData.txt"))

        // assert
        assertThat(nodes.keys).containsExactly("AAA", "BBB", "CCC", "DDD", "EEE", "GGG", "ZZZ")
        assertThat(nodes.values).containsExactly(
            Node("AAA", "BBB", "CCC"),
            Node("BBB", "DDD", "EEE"),
            Node("CCC", "ZZZ", "GGG"),
            Node("DDD", "DDD", "DDD"),
            Node("EEE", "EEE", "EEE"),
            Node("GGG", "GGG", "GGG"),
            Node("ZZZ", "ZZZ", "ZZZ"),
        )
    }

    @Test
    fun should_return_key_of_left_node() {

        // act
        val nodeKey = Node("AAA", "BBB", "CCC").next('L')

        // assert
        assertThat(nodeKey).isEqualTo("BBB")

    }

    @Test
    fun should_return_key_of_right_node() {

        // act
        val nodeKey = Node("AAA", "BBB", "CCC").next('R')

        // assert
        assertThat(nodeKey).isEqualTo("CCC")
    }

    @Test
    fun should_count_steps_of_example_1() {
        // arrange
        val nodes = mapOf(
            "AAA" to Node("AAA", "BBB", "CCC"),
            "BBB" to Node("BBB", "DDD", "EEE"),
            "CCC" to Node("CCC", "ZZZ", "GGG"),
            "DDD" to Node("DDD", "DDD", "DDD"),
            "EEE" to Node("EEE", "EEE", "EEE"),
            "GGG" to Node("GGG", "GGG", "GGG"),
            "ZZZ" to Node("ZZZ", "ZZZ", "ZZZ"),
        )
        val instructions = "RL"

        // act
        val steps = Day08().countSteps(instructions, nodes)

        // assert
        assertThat(steps).isEqualTo(2)
    }

    @Test
    fun should_count_steps_of_example_2() {
        // arrange
        val nodes = mapOf(
            "AAA" to Node("AAA", "BBB", "BBB"),
            "BBB" to Node("BBB", "AAA", "ZZZ"),
            "ZZZ" to Node("ZZZ", "ZZZ", "ZZZ"),
        )
        val instructions = "LLR"

        // act
        val steps = Day08().countSteps(instructions, nodes)

        // assert
        assertThat(steps).isEqualTo(6)
    }

    @Test
    fun should_cycle_index_through_list() {
        // arrange
        val instructions = "LLR"

        // act
        val instruction = instructions.nth(5)

        // assert
        assertThat(instruction).isEqualTo('R')
    }

    @Test
    fun should_return_all_nodes_ending_with_A() {
        // arrange
        val nodes = listOf(
            Node("11A", "11B", "XXX"),
            Node("11B", "XXX", "11Z"),
            Node("11Z", "11B", "XXX"),
            Node("22A", "22B", "XXX"),
            Node("22B", "22C", "22C"),
            Node("22C", "22Z", "22Z"),
            Node("22Z", "22B", "22B"),
            Node("XXX", "XXX", "XXX"),
        )

        // act
        val startingNodes = Day08().startingNodes(nodes)

        // assert
        assertThat(startingNodes).containsExactly(
            Node("11A", "11B", "XXX"),
            Node("22A", "22B", "XXX"),
        )
    }

    @Test
    fun should_count_steps_simultaneously() {
        // arrange
        val nodes = mapOf(
            "11A" to Node("11A", "11B", "XXX"),
            "11B" to Node("11B", "XXX", "11Z"),
            "11T" to Node("11Z", "11B", "XXX"),
            "22A" to Node("22A", "22B", "XXX"),
            "22B" to Node("22B", "22C", "22C"),
            "22C" to Node("22C", "22Z", "22Z"),
            "22Z" to Node("22Z", "22B", "22B"),
            "XXX" to Node("XXX", "XXX", "XXX"),
        )
        val instructions = "LR"

        // act
        val steps = Day08().countStepsSimultaneously(instructions, nodes)

        // assert
        assertThat(steps).isEqualTo(6)

    }

    @Test
    fun should_return_true_when_starting_node() {

        // act
        val result = Node("11A", "11B", "XXX").isStartingNode()

        // assert
        assertThat(result).isTrue()
    }

    @Test
    fun should_return_false_when_not_starting_node() {

        // act
        val result = Node("11B", "XXX", "11Z").isStartingNode()

        // assert
        assertThat(result).isFalse()
    }

    @Test
    fun should_return_true_when_end_node() {
        // act
        val result = Node("11Z", "11B", "XXX").isEndNode()

        // assert
        assertThat(result).isTrue()
    }

    @Test
    fun should_return_false_when_not_end_node() {
        // act
        val result = Node("22B", "22C", "22C").isEndNode()

        // assert
        assertThat(result).isFalse()
    }
}