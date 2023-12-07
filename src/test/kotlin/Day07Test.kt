import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day07Test {

    val HIGH_CARD = Hand("12345", 0)
    val ONE_PAIR = Hand("AA123", 0)
    val TWO_PAIR = Hand("AA1BB", 0)
    val THREE_OF_A_KIND = Hand("AAA12", 0)
    val FULL_HOUSE = Hand("AAABB", 0)
    val FOUR_OF_A_KIND = Hand("AAAAB", 0)
    val FIVE_OF_A_KIND = Hand("AAAAA", 0)

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

    @Test
    fun should_lesser_when_comparing_HIGH_CARD_with_ONE_PAIR() {

        // act
        val result = HandComparator().compare(HIGH_CARD, ONE_PAIR)


        // assert
        assertThat(result).isEqualTo(-1)
    }

    @Test
    fun should_equal_when_comparing_HIGH_CARD_with_HIGH_CARD() {

        // act
        val result = HandComparator().compare(HIGH_CARD, HIGH_CARD)


        // assert
        assertThat(result).isEqualTo(0)
    }

    @Test
    fun should_return_greater_when_comparing_ONE_PAIR_with_HIGH_CARD() {

        // act
        val result = HandComparator().compare(ONE_PAIR, HIGH_CARD)


        // assert
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun should_compare_hands_of_same_type_but_different_cards() {
        // arrange
        val greaterHand = Hand("KK677", 0)
        val lowerHand = Hand("KTJJT", 0)

        // act
        val result = HandComparator().compare(greaterHand, lowerHand)

        // assert
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun should_sort_hands() {
        // arrange
        val hands = listOf(
            Hand("32T3K", 765),
            Hand("T55J5", 684),
            Hand("KK677", 28),
            Hand("KTJJT", 220),
            Hand("QQQJA", 483),
        )

        // act
        val orderedHands = Day07().orderHands(hands)

        // assert
        assertThat(orderedHands).containsExactly(
            Hand("32T3K", 765),
            Hand("KTJJT", 220),
            Hand("KK677", 28),
            Hand("T55J5", 684),
            Hand("QQQJA", 483)
        )
    }

}