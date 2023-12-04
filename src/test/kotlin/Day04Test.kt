import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day04Test {

    @Test
    fun should_load_data() {

        // act
        val rawData = Day04().loadData(Paths.get("src", "test", "resources", "Day04_TestData.txt"))

        // assert
        assertThat(rawData).hasSize(6)

    }

    @Test
    fun should_create_card() {
        // arrange

        // act
        val card = Day04().createCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        // assert
        assertThat(card.winningNumbers).containsExactly(41, 48, 83, 86, 17)
        assertThat(card.numbers).containsExactly(83, 86, 6, 31, 17, 9, 48, 53)
    }

}