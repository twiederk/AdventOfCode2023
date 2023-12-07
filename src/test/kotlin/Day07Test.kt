import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day07Test {

    @Test
    fun should_load_hands() {

        // act
        val hands = Day07().loadHands(Paths.get("src", "test", "resources", "Day07_TestData.txt"))

        // assert
        assertThat(hands).contains(
            Hand("32T3K", 765),
            Hand("T55J5", 684),
            Hand("KK677", 28),
            Hand("KTJJT", 220),
            Hand("QQQJA", 483),
        )
    }

}