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
        val history = listOf(0L, 3L, 6L, 9L, 12L, 15L)

        // act
        val nextValue = Day09().predictNextValue(history)

        // assert
        assertThat(nextValue).isEqualTo(18)
    }

    @Test
    fun should_predict_next_value_test_data_2() {
        // arrange
        val history = listOf(1L, 3L, 6L, 10L, 15L, 21L)

        // act
        val nextValue = Day09().predictNextValue(history)

        // assert
        assertThat(nextValue).isEqualTo(28)
    }

    @Test
    fun should_predict_next_value_test_data_3() {
        // arrange
        val history = listOf(10L, 13L, 16L, 21L, 30L, 45L)

        // act
        val nextValue = Day09().predictNextValue(history)

        // assert
        assertThat(nextValue).isEqualTo(68)
    }

    @Test
    fun should_predict_next_value_real_data() {
        // arrange
        val history = listOf(
            1L,
            -2L,
            -13L,
            -26L,
            -23L,
            28L,
            189L,
            597L,
            1553L,
            3670L,
            8095L,
            16820L,
            33097L,
            61972L,
            110953L,
            190827L,
            316641L,
            508862L,
            794731L,
            1209826L,
            1799849L
        )

        // act
        val nextValue = Day09().predictNextValue(history)

        // assert
        assertThat(nextValue).isEqualTo(2622652L)
    }

    @Test
    fun should_sum_all_next_values() {
        // arrange
        val histories = listOf(
            listOf(0L, 3L, 6L, 9L, 12L, 15),
            listOf(1L, 3L, 6L, 10L, 15L, 21),
            listOf(10L, 13L, 16L, 21L, 30L, 45),
        )

        // act
        val nextValue = Day09().sumOfNextValues(histories)

        // assert
        assertThat(nextValue).isEqualTo(114)
    }

}