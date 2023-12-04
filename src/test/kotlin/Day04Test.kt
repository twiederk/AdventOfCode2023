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

        // act
        val card = Day04().createCard("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53")

        // assert
        assertThat(card.winningNumbers).containsExactly(41, 48, 83, 86, 17)
        assertThat(card.numbers).containsExactly(83, 86, 6, 31, 17, 9, 48, 53)
    }

    @Test
    fun should_count_number_of_wins() {
        // arrange
        val card = Card(listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17, 9, 48, 53))

        // act
        val wins = card.wins()

        // assert
        assertThat(wins).contains(48, 83, 17, 86)
    }

    @Test
    fun should_return_points_of_card() {
        // arrange
        val card = Card(listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17, 9, 48, 53))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(8)
    }

    @Test
    fun should_return_points_of_wins_0() {
        // arrange
        val card = Card(listOf(12), listOf(1))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(0)
    }

    @Test
    fun should_return_points_of_wins_1() {
        // arrange
        val card = Card(listOf(12), listOf(12))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(1)
    }

}