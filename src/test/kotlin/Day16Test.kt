import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day16Test {

    val contraption = listOf(
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

    @Test
    fun should_load_contraption() {

        // act
        val contraption = Day16().loadContraption(Paths.get("src", "test", "resources", "Day16_TestData.txt"))

        // assert
        assertThat(contraption.data).hasSize(10)
    }

    @Test
    fun should_return_next_beams() {
        // arrange

        // act
//        val beams = Day16().next(beams)

        // assert

    }
}