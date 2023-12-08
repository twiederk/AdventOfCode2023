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

}