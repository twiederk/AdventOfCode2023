import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day17Test {

    val heatMap = Day17().loadHeatMap(Paths.get("src", "test", "resources", "Day17_TestData.txt"))


    @Test
    fun should_load_heat_map() {

        // act
        val heatMap = Day17().loadHeatMap(Paths.get("src", "test", "resources", "Day17_TestData.txt"))

        // assert
        assertThat(heatMap).hasSize(13)
    }

    @Test
    @Disabled
    fun should_solve_part_1_with_A_Star_Algorithm() {

        // act
        val heatLoss = Day17().aStar(heatMap)

        // assert
        assertThat(heatLoss).isEqualTo(102)
    }

    @Test
    fun should_expand_two_neighbor_nodes_when_current_is_upper_left_corner() {
        // arrange
        val currentAStarNode = AStarNode(Point2D(0, 0))

        // act
        val neighbors = Day17().expandNode(heatMap, currentAStarNode, emptyList())

        // assert
        assertThat(neighbors).hasSize(2)
    }

    @Test
    fun should_expand_one_neighbor_when_one_is_already_in_closed_list() {
        // arrange
        val currentAStarNode = AStarNode(Point2D(0, 0))

        // act
        val neighbors = Day17().expandNode(heatMap, currentAStarNode, listOf(AStarNode(Point2D(0, 1))))

        // assert
        assertThat(neighbors).hasSize(1)
    }
}