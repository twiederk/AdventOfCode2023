import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day15Test {

    @Test
    fun should_load_initialization_sequence() {
        // arrange

        // act
        val initializationSequence =
            Day15().loadInitializationSequence(Paths.get("src", "test", "resources", "Day15_TestData.txt"))

        // assert
        assertThat(initializationSequence).containsExactly(
            "rn=1",
            "cm-",
            "qp=3",
            "cm=2",
            "qp-",
            "pc=4",
            "ot=9",
            "ab=5",
            "pc-",
            "pc=6",
            "ot=7",
        )
    }

    @Test
    fun should_calculate_hash_code() {

        // act
        val hashCode = Day15().calculateHashCode("HASH")

        // assert
        assertThat(hashCode).isEqualTo(52)
    }

    @Test
    fun should_calculate_initialization_sequence() {
        // arrange
        val initializationSequence = listOf(
            "rn=1",
            "cm-",
            "qp=3",
            "cm=2",
            "qp-",
            "pc=4",
            "ot=9",
            "ab=5",
            "pc-",
            "pc=6",
            "ot=7",
        )

        // act
        val part1 = Day15().part1(initializationSequence)

        // assert
        assertThat(part1).isEqualTo(1320)

    }
}