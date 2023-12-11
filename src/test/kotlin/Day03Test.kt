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

    @Test
    fun should_find_symbol() {
        // arrange

        // act
        val symbols: List<Symbol> = Day03().findSymbolsInLine("...*......", 1)

        // assert
        assertThat(symbols).hasSize(1)
        assertThat(symbols[0].column).isEqualTo(3)
        assertThat(symbols[0].row).isEqualTo(1)
        assertThat(symbols[0].symbol).isEqualTo('*')
    }

    @Test
    fun should_find_all_symbols() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val symbolList: List<Symbol> = Day03().findAllSymbolsInEngineSchematic(engineSchematic)

        // assert
        assertThat(symbolList).hasSize(6)
        assertThat(symbolList[0]).isEqualTo(Symbol(3, 1, '*'))
        assertThat(symbolList[1]).isEqualTo(Symbol(6, 3, '#'))
        assertThat(symbolList[2]).isEqualTo(Symbol(3, 4, '*'))
        assertThat(symbolList[3]).isEqualTo(Symbol(5, 5, '+'))
        assertThat(symbolList[4]).isEqualTo(Symbol(3, 8, '$'))
        assertThat(symbolList[5]).isEqualTo(Symbol(5, 8, '*'))
    }

    @Test
    fun should_return_neighbor_coords() {
        // arrange

        // act
        val neighborCoords = Symbol(1, 1, '#').neighborCoords()

        // assert
        assertThat(neighborCoords).hasSize(8)
        assertThat(neighborCoords[0]).isEqualTo(Point2D(0, 0))
        assertThat(neighborCoords[1]).isEqualTo(Point2D(1, 0))
        assertThat(neighborCoords[2]).isEqualTo(Point2D(2, 0))

        assertThat(neighborCoords[3]).isEqualTo(Point2D(0, 1))
        assertThat(neighborCoords[4]).isEqualTo(Point2D(2, 1))

        assertThat(neighborCoords[5]).isEqualTo(Point2D(0, 2))
        assertThat(neighborCoords[6]).isEqualTo(Point2D(1, 2))
        assertThat(neighborCoords[7]).isEqualTo(Point2D(2, 2))
    }

    @Test
    fun should_return_neighbor_numbers() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val neighbors = Symbol(3, 1, '*').neighborNumbers(engineSchematic)

        // assert
        assertThat(neighbors).contains(Point2D(2, 0), Point2D(2, 2), Point2D(3, 2))
    }

    @Test
    fun should_scan_part_number_from_last_digit() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val partNumber = Day03().scanPartNumber(engineSchematic, Point2D(2, 0))

        // assert
        assertThat(partNumber).isEqualTo(PartNumber(Point2D(0, 0), 467))
    }

    @Test
    fun should_scan_part_number_from_first_digit() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val partNumber = Day03().scanPartNumber(engineSchematic, Point2D(0, 0))

        // assert
        assertThat(partNumber).isEqualTo(PartNumber(Point2D(0, 0), 467))
    }

    @Test
    fun should_scan_part_number_from_middle_of_digit() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val partNumber = Day03().scanPartNumber(engineSchematic, Point2D(1, 0))

        // assert
        assertThat(partNumber).isEqualTo(PartNumber(Point2D(0, 0), 467))
    }

    @Test
    fun should_return_number_parts_of_1st_symbol() {
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )
        val neighborNumbers = listOf(Point2D(2, 0), Point2D(2, 2), Point2D(3, 2))

        // act
        val partNumbers = Day03().scanPartNumbers(engineSchematic, neighborNumbers)

        // assert
        assertThat(partNumbers).contains(
            PartNumber(Point2D(0, 0), 467),
            PartNumber(Point2D(2, 2), 35)
        )
    }

    @Test
    fun should_return_number_parts_of_2nd_symbol() {
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )
        val neighborNumbers = listOf(Point2D(6, 2), Point2D(7, 2))

        // act
        val partNumbers = Day03().scanPartNumbers(engineSchematic, neighborNumbers)

        // assert
        assertThat(partNumbers).contains(
            PartNumber(Point2D(6, 2), 633),
        )
    }

    @Test
    fun should_sum_part_numbers() {
        // arrange
        val partNumbers = setOf(
            PartNumber(Point2D(0, 0), 467),
            PartNumber(Point2D(2, 2), 35)
        )

        // act
        val sum = Day03().sumPartNumbers(partNumbers)

        // assert
        assertThat(sum).isEqualTo(502)
    }

    @Test
    fun should_find_all_numberParts() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val result = Day03().findAllPartNumbers(engineSchematic)

        // assert
        assertThat(result).contains(
            PartNumber(Point2D(0, 0), 467),
            PartNumber(Point2D(2, 2), 35),
            PartNumber(Point2D(6, 2), 633),
            PartNumber(Point2D(0, 4), 617),
            PartNumber(Point2D(2, 6), 592),
            PartNumber(Point2D(6, 7), 755),
            PartNumber(Point2D(1, 9), 664),
            PartNumber(Point2D(5, 9), 598),
        )
        // sum => 4.361

    }

    @Test
    fun should_execute_part_1() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val result = Day03().executePart1(engineSchematic)

        // assert
        assertThat(result).isEqualTo(4361)
    }

    @Test
    fun should_filter_gears() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )
        val symbolList = Day03().findAllSymbolsInEngineSchematic(engineSchematic)

        // act
        val gearList = Day03().isGear(engineSchematic, symbolList)

        // assert
        assertThat(gearList).containsOnly(
            Symbol(3, 1, '*'),
            Symbol(5, 8, '*'),
        )

    }

    @Test
    fun should_execute_part_2() {
        // arrange
        val engineSchematic = listOf(
            "467..114..",
            "...*......",
            "..35..633.",
            "......#...",
            "617*......",
            ".....+.58.",
            "..592.....",
            "......755.",
            "...$.*....",
            ".664.598..",
        )

        // act
        val result = Day03().executePart2(engineSchematic)

        // assert
        assertThat(result).isEqualTo(467835)
    }

}