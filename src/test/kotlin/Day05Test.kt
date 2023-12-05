import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day05Test {

    @Test
    fun should_create_mappings() {
        // arrange
        val rangeInfo = RangeInfo(50, 98, 2)

        // act
        val mapEntries = Day05().createMappings(rangeInfo)

        // assert
        assertThat(mapEntries).contains(
            Mapping(98, 50),
            Mapping(99, 51),
        )
    }

    @Test
    fun should_create_mapping_table() {
        // arrange
        val rangeInfos = listOf(
            RangeInfo(50, 98, 2),
            RangeInfo(52, 50, 48),
        )

        // act
        val mappingTable = Day05().createMappingTable(rangeInfos)

        // assert
        assertThat(mappingTable).hasSize(50)

    }
}