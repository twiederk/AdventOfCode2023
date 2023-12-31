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

    @Test
    fun should_read_first_word() {

        // act
        val firstWord = Day01().readFirstWord("two1nine")

        // assert
        assertThat(firstWord).isEqualTo('2')
    }

    @Test
    fun should_read_last_word() {

        // act
        val lastWord = Day01().readLastWord("two1nine")

        // assert
        assertThat(lastWord).isEqualTo('9')
    }

    @Test
    fun should_convert_word_to_digit() {

        // act
        val digit = Day01().convertWordToDigit("one")

        // assert
        assertThat(digit).isEqualTo('1')
    }

    @Test
    fun should_read_calibration_value_with_words_two1nine() {

        // act
        val calibrationValue = Day01().readCalibrationValueWithWords("two1nine")

        // assert
        assertThat(calibrationValue).isEqualTo(29)
    }

    @Test
    fun should_read_calibration_value_with_words_eightwothree() {

        // act
        val calibrationValue = Day01().readCalibrationValueWithWords("eightwothree")

        // assert
        assertThat(calibrationValue).isEqualTo(83)
    }

    @Test
    fun should_read_calibration_value_with_words_abcone2threexyz() {

        // act
        val calibrationValue = Day01().readCalibrationValueWithWords("abcone2threexyz")

        // assert
        assertThat(calibrationValue).isEqualTo(13)
    }

    @Test
    fun should_read_calibration_value_with_words_xtwone3four() {

        // act
        val calibrationValue = Day01().readCalibrationValueWithWords("xtwone3four")

        // assert
        assertThat(calibrationValue).isEqualTo(24)
    }

    @Test
    fun should_read_calibration_value_with_words_4nineeightseven2() {

        // act
        val calibrationValue = Day01().readCalibrationValueWithWords("4nineeightseven2")

        // assert
        assertThat(calibrationValue).isEqualTo(42)
    }

    @Test
    fun should_read_calibration_value_with_words_zoneight234() {

        // act
        val calibrationValue = Day01().readCalibrationValueWithWords("zoneight234")

        // assert
        assertThat(calibrationValue).isEqualTo(14)
    }

    @Test
    fun should_read_calibration_value_with_words_7pqrstsixteen() {

        // act
        val calibrationValue = Day01().readCalibrationValueWithWords("7pqrstsixteen")

        // assert
        assertThat(calibrationValue).isEqualTo(76)
    }

    @Test
    fun should_sum_up_calibration_values_with_words() {
        // arrange
        val calibrationValues = listOf(
            "two1nine",
            "eightwothree",
            "abcone2threexyz",
            "xtwone3four",
            "4nineeightseven2",
            "zoneight234",
            "7pqrstsixteen"
        )

        // act
        val sumOfCalibrationValues = Day01().sumUpCalibrationValuesWithWords(calibrationValues)

        // assert
        assertThat(sumOfCalibrationValues).isEqualTo(281)
    }
}