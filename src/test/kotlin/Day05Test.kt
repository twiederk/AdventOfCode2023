import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day05Test {

    private val almanac = Almanac(
        listOf(
            CategoryTable(
                listOf(
                    RangeInfo(50, 98, 2),
                    RangeInfo(52, 50, 48),
                )
            ),
            CategoryTable(
                listOf(
                    RangeInfo(0, 15, 37),
                    RangeInfo(37, 52, 2),
                    RangeInfo(39, 0, 15),
                )
            ),
            CategoryTable(
                listOf(
                    RangeInfo(49, 53, 8),
                    RangeInfo(0, 11, 42),
                    RangeInfo(42, 0, 7),
                    RangeInfo(57, 7, 4),
                )
            ),

            CategoryTable(
                listOf(
                    RangeInfo(88, 18, 7),
                    RangeInfo(18, 25, 70),
                )
            ),

            CategoryTable(
                listOf(
                    RangeInfo(45, 77, 23),
                    RangeInfo(81, 45, 19),
                    RangeInfo(68, 64, 13),
                )
            ),

            CategoryTable(
                listOf(
                    RangeInfo(0, 69, 1),
                    RangeInfo(1, 0, 69),
                )
            ),

            CategoryTable(
                listOf(
                    RangeInfo(60, 56, 37),
                    RangeInfo(56, 93, 4),
                )
            ),

            )
    )

    @Test
    fun should_return_dest_category_when_in_source_category() {

        // act
        val destCategory = RangeInfo(52, 50, 78).getDestCategory(79)

        // assert
        assertThat(destCategory).isEqualTo(81)
    }

    @Test
    fun should_return_NOT_IN_RANGE_when_not_in_source_category() {

        // act
        val destCategory = RangeInfo(52, 50, 48).getDestCategory(14)

        // assert
        assertThat(destCategory).isEqualTo(RangeInfo.NOT_IN_RANGE)
    }

    @Test
    fun should_return_dest_category_from_CategoryTable_for_seed_79() {
        // arrange
        val categoryTable = CategoryTable(
            listOf(
                RangeInfo(50, 98, 2),
                RangeInfo(52, 50, 48),
            )
        )

        // act
        val destCategory = categoryTable.getDestCategory(79)

        // assert
        assertThat(destCategory).isEqualTo(81)
    }

    @Test
    fun should_return_dest_category_from_CategoryTable_for_seed_14() {
        // arrange
        val categoryTable = CategoryTable(
            listOf(
                RangeInfo(50, 98, 2),
                RangeInfo(52, 50, 48),
            )
        )

        // act
        val destCategory = categoryTable.getDestCategory(14)

        // assert
        assertThat(destCategory).isEqualTo(14)
    }

    @Test
    fun should_return_dest_category_from_CategoryTable_for_seed_55() {
        // arrange
        val categoryTable = CategoryTable(
            listOf(
                RangeInfo(50, 98, 2),
                RangeInfo(52, 50, 48),
            )
        )

        // act
        val destCategory = categoryTable.getDestCategory(55)

        // assert
        assertThat(destCategory).isEqualTo(57)
    }

    @Test
    fun should_return_dest_category_from_CategoryTable_for_seed_13() {
        // arrange
        val categoryTable = CategoryTable(
            listOf(
                RangeInfo(50, 98, 2),
                RangeInfo(52, 50, 48),
            )
        )

        // act
        val destCategory = categoryTable.getDestCategory(13)

        // assert
        assertThat(destCategory).isEqualTo(13)
    }

    @Test
    fun should_return_final_category_from_Almanac_for_seed_79() {

        // act
        val location = almanac.getLocation(79)

        // assert
        assertThat(location).isEqualTo(82)
    }

    @Test
    fun should_return_final_category_from_Almanac_for_seed_14() {

        // act
        val location = almanac.getLocation(14)

        // assert
        assertThat(location).isEqualTo(43)
    }

    @Test
    fun should_return_final_category_from_Almanac_for_seed_55() {

        // act
        val location = almanac.getLocation(55)

        // assert
        assertThat(location).isEqualTo(86)
    }


    @Test
    fun should_return_final_category_from_Almanac_for_seed_13() {

        // act
        val location = almanac.getLocation(13)

        // assert
        assertThat(location).isEqualTo(35)
    }

    @Test
    fun should_return_lowest_location() {

        // act
        val lowestLocation = almanac.getLowestLocation(listOf(79, 14, 55, 13))

        // assert
        assertThat(lowestLocation).isEqualTo(35)
    }

    @Test
    fun should_load_list_of_seeds() {

        // act
        val seeds = Day05().loadSeeds(Paths.get("src", "test", "resources", "Day05_TestData.txt"))

        // assert
        assertThat(seeds).contains(79, 14, 55, 13)
    }

    @Test
    fun should_load_Almanac() {

        // act
        val almanac = Day05().loadAlmanac(Paths.get("src", "test", "resources", "Day05_TestData.txt"))

        // assert
        assertThat(almanac.categoryTables).hasSize(7)
        assertThat(almanac.getLowestLocation(listOf(79, 14, 55, 13))).isEqualTo(35)
    }
}