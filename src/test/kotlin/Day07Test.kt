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

    @Test
    fun should_group_cards_of_hand_1() {
        // arrange

        // act
        val groups = Hand("32T3K", 765).groupCards()

        // assert
        assertThat(groups).hasSize(4)
        assertThat(groups['3']).isEqualTo(listOf('3', '3'))
        assertThat(groups['2']).isEqualTo(listOf('2'))
        assertThat(groups['T']).isEqualTo(listOf('T'))
        assertThat(groups['K']).isEqualTo(listOf('K'))
    }

    @Test
    fun should_group_cards_of_hand_2() {
        // arrange

        // act
        val groups = Hand("T55J5", 684).groupCards()

        // assert
        assertThat(groups).hasSize(3)
        assertThat(groups['5']).isEqualTo(listOf('5', '5', '5'))
        assertThat(groups['T']).isEqualTo(listOf('T'))
        assertThat(groups['J']).isEqualTo(listOf('J'))
    }

    @Test
    fun should_return_strength_of_ONE_PAIR() {

        // act
        val strength = Hand("32T3K", 765).strength()

        // assert
        assertThat(strength).isEqualTo(Strength.ONE_PAIR)
    }

    @Test
    fun should_return_strength_THREE_OF_A_KIND() {

        // act
        val strength = Hand("T55J5", 684).strength()

        // assert
        assertThat(strength).isEqualTo(Strength.THREE_OF_A_KIND)
    }

    @Test
    fun should_return_strength_of_TWO_PAIR() {

        // act
        val strength = Hand("KK677", 28).strength()

        // assert
        assertThat(strength).isEqualTo(Strength.TWO_PAIR)
    }

    @Test
    fun should_return_strength_of_FIVE_OF_A_KIND() {

        // act
        val strength = Hand("AAAAA", 0).strength()

        // assert
        assertThat(strength).isEqualTo(Strength.FIVE_OF_A_KIND)
    }

    @Test
    fun should_return_strength_of_FOUR_OF_A_KIND() {

        // act
        val strength = Hand("AA1AA", 0).strength()

        // assert
        assertThat(strength).isEqualTo(Strength.FOUR_OF_A_KIND)
    }

    @Test
    fun should_return_strength_of_FULL_HOUSE() {

        // act
        val strength = Hand("AABBA", 0).strength()

        // assert
        assertThat(strength).isEqualTo(Strength.FULL_HOUSE)
    }

    @Test
    fun should_return_strength_of_HIGH_CARD() {

        // act
        val strength = Hand("ABCDE", 0).strength()

        // assert
        assertThat(strength).isEqualTo(Strength.HIGH_CARD)
    }

}