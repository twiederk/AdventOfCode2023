import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day09Test {

    @Test
    fun should_load_histories() {

        // act
        val histories = Day09().loadHistories(Paths.get("src", "test", "resources", "Day09_TestData.txt"))

        // assert
        assertThat(histories).hasSize(3)
    }

    @Test
    fun should_predict_next_value_test_data_1() {
        // arrange
        val history = listOf(0, 3, 6, 9, 12, 15)

        // act
        val nextValue = Day09().predictNextValue(history)

        // assert
        assertThat(nextValue).isEqualTo(18)
    }

    @Test
    fun should_predict_next_value_test_data_2() {
        // arrange
        val history = listOf(1, 3, 6, 10, 15, 21)

        // act
        val nextValue = Day09().predictNextValue(history)

        // assert
        assertThat(nextValue).isEqualTo(28)
    }

    @Test
    fun should_predict_next_value_test_data_3() {
        // arrange
        val history = listOf(10, 13, 16, 21, 30, 45)

        // act
        val nextValue = Day09().predictNextValue(history)

        // assert
        assertThat(nextValue).isEqualTo(68)
    }

    @Test
    fun should_sum_all_next_values() {
        // arrange
        val histories = listOf(
            listOf(0, 3, 6, 9, 12, 15),
            listOf(1, 3, 6, 10, 15, 21),
            listOf(10, 13, 16, 21, 30, 45),
        )

        // act
        val nextValue = Day09().sumOfNextValues(histories)

        // assert
        assertThat(nextValue).isEqualTo(114)
    }

}