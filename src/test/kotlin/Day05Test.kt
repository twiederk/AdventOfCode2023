import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day05Test {

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

}