import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day18Test {

    private val digInstructions = listOf(
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

    @Test
    fun should_decode_color_code() {

        // act
        val digInstruction = DigInstruction(direction = 'R', meters = 6, colorCode = "(#70c710)").decodeColorCode()

        // assert
        assertThat(digInstruction).isEqualTo(DigInstruction('R', 461937, "(#70c710)"))
    }

    @Test
    fun should_decode_all_color_codes() {

        // act
        val digInstructions = Day18().decodeColorCodes(digInstructions)

        // assert
        assertThat(digInstructions).containsExactly(
            DigInstruction(direction = 'R', meters = 461937, colorCode = "(#70c710)"),
            DigInstruction(direction = 'D', meters = 56407, colorCode = "(#0dc571)"),
            DigInstruction(direction = 'R', meters = 356671, colorCode = "(#5713f0)"),
            DigInstruction(direction = 'D', meters = 863240, colorCode = "(#d2c081)"),
            DigInstruction(direction = 'R', meters = 367720, colorCode = "(#59c680)"),
            DigInstruction(direction = 'D', meters = 266681, colorCode = "(#411b91)"),
            DigInstruction(direction = 'L', meters = 577262, colorCode = "(#8ceee2)"),
            DigInstruction(direction = 'U', meters = 829975, colorCode = "(#caa173)"),
            DigInstruction(direction = 'L', meters = 112010, colorCode = "(#1b58a2)"),
            DigInstruction(direction = 'D', meters = 829975, colorCode = "(#caa171)"),
            DigInstruction(direction = 'L', meters = 491645, colorCode = "(#7807d2)"),
            DigInstruction(direction = 'U', meters = 686074, colorCode = "(#a77fa3)"),
            DigInstruction(direction = 'L', meters = 5411, colorCode = "(#015232)"),
            DigInstruction(direction = 'U', meters = 500254, colorCode = "(#7a21e3)")
        )
    }

    @Test
    fun should_instantiate_lagoon() {

        // act
        val lagoon = Lagoon()

        // assert
        val digger = lagoon.digger
        assertThat(digger.position).isEqualTo(Point2D(0, 0))
        assertThat(digger.facing).isEqualTo(Point2D.NORTH)
    }

    @Test
    fun should_create_trench_when_digging() {

        // act
        val trench = Digger().dig(DigInstruction(direction = 'R', meters = 6, colorCode = "(#70c710)"))

        // assert
        assertThat(trench).containsExactly(
            Point2D(1, 0),
            Point2D(2, 0),
            Point2D(3, 0),
            Point2D(4, 0),
            Point2D(5, 0),
            Point2D(6, 0),
        )
    }

    @Test
    fun should_create_trench_when_digging_all_instructions() {
        // arrange
        val digInstructions = listOf(
            DigInstruction(direction = 'R', meters = 6, colorCode = "(#70c710)"),
            DigInstruction(direction = 'D', meters = 5, colorCode = "(#0dc571)"),
            DigInstruction(direction = 'L', meters = 2, colorCode = "(#5713f0)"),
//            DigInstruction(direction = 'D', meters = 2, colorCode = "(#d2c081)"),
//            DigInstruction(direction = 'R', meters = 2, colorCode = "(#59c680)"),
//            DigInstruction(direction = 'D', meters = 2, colorCode = "(#411b91)"),
//            DigInstruction(direction = 'L', meters = 5, colorCode = "(#8ceee2)"),
//            DigInstruction(direction = 'U', meters = 2, colorCode = "(#caa173)"),
//            DigInstruction(direction = 'L', meters = 1, colorCode = "(#1b58a2)"),
//            DigInstruction(direction = 'U', meters = 2, colorCode = "(#caa171)"),
//            DigInstruction(direction = 'R', meters = 2, colorCode = "(#7807d2)"),
//            DigInstruction(direction = 'U', meters = 3, colorCode = "(#a77fa3)"),
//            DigInstruction(direction = 'L', meters = 2, colorCode = "(#015232)"),
//            DigInstruction(direction = 'U', meters = 2, colorCode = "(#7a21e3)")
        )

        // act
        val trench = Digger().digAll(digInstructions)

        // assert
        assertThat(trench).containsExactly(
            Point2D(1, 0),
            Point2D(2, 0),
            Point2D(3, 0),
            Point2D(4, 0),
            Point2D(5, 0),
            Point2D(6, 0),
            Point2D(6, 1),
            Point2D(6, 2),
            Point2D(6, 3),
            Point2D(6, 4),
            Point2D(6, 5),
            Point2D(5, 5),
            Point2D(4, 5),
        )
    }

    @Test
    fun should_dig_trench_when_executing_dig_instructions() {

        // assert
        val lagoon = Lagoon()

        // act
        lagoon.digTrench(digInstructions)

        // assert
        assertThat(lagoon.trench).hasSize(38)
    }

    @Test
    fun should_dig_out_lagoon() {
        // arrange
        val lagoon = Lagoon()
        lagoon.digTrench(digInstructions)

        // act
        lagoon.digOut()

        // assert
        assertThat(lagoon.pool.size).isEqualTo(62)

    }

    @Test
    fun should_solve_part1_with_example_data() {
        // arrange
        val day18 = Day18()
        val digInstructions = day18.loadDigPlan(Paths.get("src", "test", "resources", "Day18_TestData.txt"))

        // act
        val cubes = day18.part1(digInstructions)

        // assert
        assertThat(cubes).isEqualTo(62)
    }

    @Test
    @Disabled("This test never returns")
    fun should_solve_part2_with_decode_dig_instructions() {
        // arrange
        val day18 = Day18()
        val digInstructions = day18.loadDigPlan(Paths.get("src", "test", "resources", "Day18_TestData.txt"))
        val decodedDigInstructions = day18.decodeColorCodes(digInstructions)

        // act
        val cubes = day18.part1(decodedDigInstructions)

        // assert
        assertThat(cubes).isEqualTo(952408144115)
    }

}
