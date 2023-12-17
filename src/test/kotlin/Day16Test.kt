import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day16Test {

    private val contraption = Contraption(
        listOf(
            """.|...\....""",
            """|.-.\.....""",
            """.....|-...""",
            """........|.""",
            """..........""",
            """.........\""",
            """..../.\\..""",
            """.-.-/..|..""",
            """.|....-|.\""",
            """..//.|....""",
        )
    )

    @Test
    fun should_load_contraption() {

        // act
        val contraption = Day16().loadContraption(Paths.get("src", "test", "resources", "Day16_TestData.txt"))

        // assert
        assertThat(contraption.data).hasSize(10)
    }

    @Test
    fun should_return_next_beams_for_a_single_beam() {

        // act
        val beams = contraption.nextSingle(Beam(Point2D(0, 0), Point2D.EAST))

        // assert
        assertThat(beams).containsExactly(
            Beam(Point2D(1, 0), Point2D.EAST)
        )
    }

    @Test
    fun should_return_next_beams_for_multiple_beams() {
        // arrange
        var beams = listOf(
            Beam(Point2D(0, 0), Point2D.EAST),
            Beam(Point2D(0, 1), Point2D.EAST)
        )

        // act
        beams = contraption.nextMultiple(beams)

        // assert
        assertThat(beams).containsExactly(
            Beam(Point2D(1, 0), Point2D.EAST),
            Beam(Point2D(0, 0), Point2D.NORTH),
            Beam(Point2D(0, 2), Point2D.SOUTH),
        )

    }
}