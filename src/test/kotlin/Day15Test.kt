import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day15Test {

    @Test
    fun should_load_initialization_sequence() {
        // arrange

        // act
        val initializationSequence =
            Day15().loadInitializationSequence(Paths.get("src", "test", "resources", "Day15_TestData.txt"))

        // assert
        assertThat(initializationSequence).containsExactly(
            "rn=1",
            "cm-",
            "qp=3",
            "cm=2",
            "qp-",
            "pc=4",
            "ot=9",
            "ab=5",
            "pc-",
            "pc=6",
            "ot=7",
        )
    }

    @Test
    fun should_calculate_hash_code() {

        // act
        val hashCode = Day15().calculateHashCode("HASH")

        // assert
        assertThat(hashCode).isEqualTo(52)
    }

    @Test
    fun should_calculate_initialization_sequence() {
        // arrange
        val initializationSequence = listOf(
            "rn=1",
            "cm-",
            "qp=3",
            "cm=2",
            "qp-",
            "pc=4",
            "ot=9",
            "ab=5",
            "pc-",
            "pc=6",
            "ot=7",
        )

        // act
        val part1 = Day15().part1(initializationSequence)

        // assert
        assertThat(part1).isEqualTo(1320)
    }

    @Test
    fun should_load_instructions() {

        // act
        val instructions = Day15().loadInstructions(Paths.get("src", "test", "resources", "Day15_TestData.txt"))

        // assert
        assertThat(instructions).containsExactly(
            Instruction("rn", '=', 1),
            Instruction("cm", '-'),
            Instruction("qp", '=', 3),
            Instruction("cm", '=', 2),
            Instruction("qp", '-'),
            Instruction("pc", '=', 4),
            Instruction("ot", '=', 9),
            Instruction("ab", '=', 5),
            Instruction("pc", '-'),
            Instruction("pc", '=', 6),
            Instruction("ot", '=', 7),

            )
    }

    @Test
    fun should_instantiate_boxes() {

        // act
        val boxes = Day15().boxes

        // assert
        assertThat(boxes).hasSize(256)
    }

    @Test
    fun should_execute_instruction_and_add_lens() {
        // arrange
        val day15 = Day15()

        // act
        day15.execute(Instruction("rn", '=', 1))

        // assert
        assertThat(day15.boxes[0].lenses).containsExactly(
            Lens("rn", 1)
        )
    }

    @Test
    fun should_execute_instruction_and_update_lens() {
        // arrange
        val day15 = Day15()
        day15.execute(Instruction("rn", '=', 1))

        // act
        day15.execute(Instruction("rn", '=', 5))

        // assert
        assertThat(day15.boxes[0].lenses).containsExactly(
            Lens("rn", 5)
        )
    }

    @Test
    fun should_add_lens_to_box() {
        // arrange
        val box = Box(0)

        // act
        box.addOrUpdate(Lens("rn", 1))

        // assert
        assertThat(box.lenses).containsExactly(
            Lens("rn", 1)
        )
    }

}