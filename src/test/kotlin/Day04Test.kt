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
        assertThat(card.number).isEqualTo(1)
        assertThat(card.winningNumbers).containsExactly(41, 48, 83, 86, 17)
        assertThat(card.numbers).containsExactly(83, 86, 6, 31, 17, 9, 48, 53)
    }

    @Test
    fun should_create_all_cards() {
        // arrange
        val data = listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
        )

        // act
        val cards = Day04().createAllCards(data)

        // assert
        assertThat(cards).hasSize(6)
    }

    @Test
    fun should_count_number_of_wins() {
        // arrange
        val card = Card(1, listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17, 9, 48, 53))

        // act
        val wins = card.wins()

        // assert
        assertThat(wins).contains(48, 83, 17, 86)
    }

    @Test
    fun should_return_points_of_card() {
        // arrange
        val card = Card(1, listOf(41, 48, 83, 86, 17), listOf(83, 86, 6, 31, 17, 9, 48, 53))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(8)
    }

    @Test
    fun should_return_points_of_wins_0() {
        // arrange
        val card = Card(0, listOf(12), listOf(1))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(0)
    }

    @Test
    fun should_return_points_of_wins_1() {
        // arrange
        val card = Card(0, listOf(12), listOf(12))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(1)
    }

    @Test
    fun should_return_points_of_wins_2() {
        // arrange
        val card = Card(0, listOf(12, 16), listOf(12, 16))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(2)
    }

    @Test
    fun should_return_points_of_wins_3() {
        // arrange
        val card = Card(0, listOf(12, 16, 20), listOf(12, 16, 20))

        // act
        val points = card.points()

        // assert
        assertThat(points).isEqualTo(4)
    }

    @Test
    fun should_sum_up_points_of_all_cards() {
        // arrange
        val data = listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
        )
        val cards = Day04().createAllCards(data)

        // act
        val totalPoints = Day04().totalPoints(cards)

        // assert
        assertThat(totalPoints).isEqualTo(13)
    }

    // store card repository
    // + add card number to card
    // create copies of scratch cards
    // add copies to won scratch cards list
    // calculate total number of scratch cards: size of won scratch cards list + size of card repository

    @Test
    fun should_create_copies_of_scratch_cards_1() {
        // arrange
        val card = Card(1, listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5))
        val cardRepository = listOf(
            Card(1, listOf(), listOf()),
            Card(2, listOf(), listOf()),
            Card(3, listOf(), listOf()),
            Card(4, listOf(), listOf()),
            Card(5, listOf(), listOf()),
            Card(6, listOf(), listOf()),
        )
        val wonCards = mutableListOf<Card>()

        // act
        card.wonCards(cardRepository, wonCards)

        // assert
        assertThat(wonCards.map { it.number }).contains(2, 3, 4, 5, 6)
    }

    @Test
    fun should_create_copies_of_scratch_cards_2() {
        // arrange
        val card = Card(1, listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5))
        val cardRepository = listOf(
            Card(1, listOf(), listOf()),
            Card(2, listOf(), listOf()),
            Card(3, listOf(), listOf()),
            Card(4, listOf(), listOf()),
            Card(5, listOf(1), listOf(1)),
            Card(6, listOf(), listOf()),
        )
        val wonCards = mutableListOf<Card>()

        // act
        card.wonCards(cardRepository, wonCards)

        // assert
        assertThat(wonCards.map { it.number }).contains(2, 3, 4, 5, 6, 6)
    }

    @Test
    fun should_create_copies_of_scratch_cards_3() {
        // arrange
        val card = Card(1, listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5))
        val cardRepository = listOf(
            Card(1, listOf(), listOf()),
            Card(2, listOf(), listOf()),
            Card(3, listOf(), listOf()),
            Card(4, listOf(), listOf()),
            Card(5, listOf(1), listOf(1)),
            Card(6, listOf(1), listOf(1)),
        )
        val wonCards = mutableListOf<Card>()

        // act
        card.wonCards(cardRepository, wonCards)

        // assert
        assertThat(wonCards.map { it.number }).contains(2, 3, 4, 5, 6, 6)
    }

    @Test
    fun should_create_copies_of_scratch_cards_out_of_bounds() {
        // arrange
        val card = Card(1, listOf(1, 2, 3, 4, 5), listOf(1, 2, 3, 4, 5))
        val cardRepository = listOf(
            Card(1, listOf(), listOf()),
            Card(2, listOf(), listOf()),
            Card(3, listOf(), listOf()),
            Card(4, listOf(1), listOf(1)),
            Card(5, listOf(1), listOf(1)),
            Card(6, listOf(1), listOf(1)),
        )
        val wonCards = mutableListOf<Card>()

        // act
        card.wonCards(cardRepository, wonCards)

        // assert
        assertThat(wonCards.map { it.number }).contains(2, 3, 4, 5, 5, 6, 6, 6)
    }

    @Test
    fun should_create_copies_all_cards() {
        // arrange
        val data = listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
        )
        val cardRepository = Day04().createAllCards(data)

        // act
        val wonCards = Day04().wonCards(cardRepository)

        // assert
        assertThat(wonCards).hasSize(24)
    }

    @Test
    fun should_count_total_of_cards() {
        // arrange
        val data = listOf(
            "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
            "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
            "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
            "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
            "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
            "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11",
        )
        val cardRepository = Day04().createAllCards(data)

        // act
        val totalNumberOfCards = Day04().totalCards(cardRepository)

        // assert
        assertThat(totalNumberOfCards).isEqualTo(30)
    }
}