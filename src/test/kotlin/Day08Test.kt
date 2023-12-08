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

}