import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day03Test {

    @Test
    fun should_load_data() {
        // arrange

        // act
        val engineSchematic = Day03().loadData(Paths.get("src", "test", "resources", "Day03_TestData.txt"))

        // assert
        assertThat(engineSchematic).hasSize(10)

    }

    @Test
    fun should_return_false_when_char_is_a_number() {
        // arrange

        // act
        val result = Day03().isSymbol('4')

        // assert
        assertThat(result).isFalse()
    }

    @Test
    fun should_return_false_when_char_is_a_dot() {
        // arrange

        // act
        val result = Day03().isSymbol('.')

        // assert
        assertThat(result).isFalse()
    }

    @Test
    fun should_return_true_when_char_is_dollar_sign() {
        // arrange

        // act
        val result = Day03().isSymbol('$')

        // assert
        assertThat(result).isTrue()
    }

}