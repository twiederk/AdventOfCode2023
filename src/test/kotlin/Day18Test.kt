import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day18Test {

    @Test
    fun should_load_dig_plan() {

        // act
        val digPlan = Day18().loadDigPlan(Paths.get("src", "test", "resources", "Day18_TestData.txt"))

        // assert
        assertThat(digPlan).containsExactly(
            DigInstruction(direction = 'R', meters = 6, colorCode = "(#70c710)"),
            DigInstruction(direction = 'D', meters = 5, colorCode = "(#0dc571)"),
            DigInstruction(direction = 'L', meters = 2, colorCode = "(#5713f0)"),
            DigInstruction(direction = 'D', meters = 2, colorCode = "(#d2c081)"),
            DigInstruction(direction = 'R', meters = 2, colorCode = "(#59c680)"),
            DigInstruction(direction = 'D', meters = 2, colorCode = "(#411b91)"),
            DigInstruction(direction = 'L', meters = 5, colorCode = "(#8ceee2)"),
            DigInstruction(direction = 'U', meters = 2, colorCode = "(#caa173)"),
            DigInstruction(direction = 'L', meters = 1, colorCode = "(#1b58a2)"),
            DigInstruction(direction = 'U', meters = 2, colorCode = "(#caa171)"),
            DigInstruction(direction = 'R', meters = 2, colorCode = "(#7807d2)"),
            DigInstruction(direction = 'U', meters = 3, colorCode = "(#a77fa3)"),
            DigInstruction(direction = 'L', meters = 2, colorCode = "(#015232)"),
            DigInstruction(direction = 'U', meters = 2, colorCode = "(#7a21e3)")
        )
    }

}