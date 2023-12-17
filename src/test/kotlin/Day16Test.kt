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

    @Test
    fun should_drop_beam_when_it_leaves_the_contraption_to_the_NORTH() {

        // act
        val beams = contraption.nextSingle(Beam(Point2D(9, 0), Point2D.NORTH))

        // assert
        assertThat(beams).isEmpty()
    }

    @Test
    fun should_drop_beam_when_it_leaves_the_contraption_to_the_EAST() {

        // act
        val beams = contraption.nextSingle(Beam(Point2D(9, 0), Point2D.EAST))

        // assert
        assertThat(beams).isEmpty()
    }

    @Test
    fun should_drop_beam_when_it_leaves_the_contraption_to_the_SOUTH() {

        // act
        val beams = contraption.nextSingle(Beam(Point2D(9, 9), Point2D.SOUTH))

        // assert
        assertThat(beams).isEmpty()
    }

    @Test
    fun should_drop_beam_when_it_leaves_the_contraption_to_the_WEST() {

        // act
        val beams = contraption.nextSingle(Beam(Point2D(0, 9), Point2D.WEST))

        // assert
        assertThat(beams).isEmpty()
    }

    @Test
    fun should_init_visited_tiles_with_starting_position() {

        // act
        val visitedTiles = contraption.visitedTiles

        // assert
        assertThat(visitedTiles).containsExactly(Point2D(0, 0))
    }

    @Test
    fun should_store_visited_tiles() {
        // arrange
        val beams = listOf(
            Beam(Point2D(0, 0), Point2D.EAST),
            Beam(Point2D(0, 1), Point2D.EAST)
        )

        // act
        contraption.nextMultiple(beams)

        // assert
        assertThat(contraption.visitedTiles).containsExactlyInAnyOrder(
            Point2D(1, 0),
            Point2D(0, 0),
            Point2D(0, 2),
        )
    }

    @Test
    fun should_run_beam_through_contraption() {

        // act
        val energizedTiles = contraption.energize()

        // assert
        println(contraption.visitedTiles)
        assertThat(energizedTiles).isEqualTo(46)
    }

}