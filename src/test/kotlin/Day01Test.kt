import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.io.path.Path

class Day01Test {

    @Test
    fun loadData() {
        // arrange
        val path = Path("src", "test", "resources", "Day01_TestData.txt")

        // act
        val items = Day01().loadData(path)

        // assert
        assertThat(items).hasSize(4)
    }

    @Test
    fun should_read_calibration_value() {

        // act
        val calibrationValue = Day01().readCalibrationValue("1abc2")

        // assert
        assertThat(calibrationValue).isEqualTo(12)
    }

    @Test
    fun should_read_first_digit() {

        // act
        val firstDigit = Day01().readFirstDigit("1abc2")

        // assert
        assertThat(firstDigit).isEqualTo('1')
    }

    @Test
    fun should_read_last_digit() {

        // act
        val lastDigit = Day01().readLastDigit("1abc2")

        // assert
        assertThat(lastDigit).isEqualTo('2')
    }

    @Test
    fun should_sum_up_calibration_values() {
        // arrange
        val calibrationValues = listOf(
            "1abc2",
            "pqr3stu8vwx",
            "a1b2c3d4e5f",
            "treb7uchet",
        )

        // act
        val sumOfCalibrationValues = Day01().sumUpCalibrationValues(calibrationValues)

        // assert
        assertThat(sumOfCalibrationValues).isEqualTo(142)

    }
}