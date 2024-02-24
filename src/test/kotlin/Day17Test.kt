import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day17Test {

    private val heatMap = Day17().loadHeatMap(Paths.get("src", "test", "resources", "Day17_TestData.txt"))


    @Test
    fun should_load_heat_map() {

        // act
        val heatMap = Day17().loadHeatMap(Paths.get("src", "test", "resources", "Day17_TestData.txt"))

        // assert
        assertThat(heatMap).hasSize(13)
    }

    @Test
    fun should_solve_part_1_with_A_Star_Algorithm() {

        // act
        val heatLoss = Day17().part1(heatMap)

        // assert
        assertThat(heatLoss).isEqualTo(102)
    }

    @Test
    fun should_expand_two_neighbor_nodes_when_current_is_upper_left_corner() {
        // arrange
        val currentHeatNode = HeatNode(Point2D(0, 0))

        // act
        val neighbors = Day17().expandNode(heatMap, currentHeatNode, emptySet(), emptyList())

        // assert
        assertThat(neighbors).hasSize(2)
    }

    @Test
    fun should_expand_one_neighbor_when_one_is_already_in_closed_list() {
        // arrange
        val currentHeatNode = HeatNode(Point2D(0, 0))
        val closedList = setOf(HeatNode(Point2D(0, 1)))

        // act
        val neighbors = Day17().expandNode(heatMap, currentHeatNode, closedList, emptyList())

        // assert
        assertThat(neighbors).hasSize(1)
    }

    @Test
    fun should_expand_one_neighbor_when_one_is_in_open_list_with_better_way() {
        // arrange
        val currentHeatNode = HeatNode(Point2D(0, 0))
        val openList = listOf(HeatNode(Point2D(0, 1)))

        // act
        val neighbors = Day17().expandNode(heatMap, currentHeatNode, emptySet(), openList)

        // assert
        assertThat(neighbors).hasSize(1)
    }

    @Test
    fun should_return_heat_loss_with_coords_0_0() {
        // arrange
        val heatMap = listOf(
            "12",
            "34",
        )

        // act
        val heatLoss = HeatNode(Point2D(0, 0)).getHeatLoss(heatMap)

        // assert
        assertThat(heatLoss).isEqualTo(1)
    }

    @Test
    fun should_return_heat_loss_with_coords_1_1() {
        // arrange
        val heatMap = listOf(
            "12",
            "34",
        )

        // act
        val heatLoss = HeatNode(Point2D(1, 1)).getHeatLoss(heatMap)

        // assert
        assertThat(heatLoss).isEqualTo(4)
    }

    @Test
    fun should_sum_up_heat_loss_of_all_heat_node_when_path_is_given() {
        // arrange
        val heatMap = listOf(
            "12",
            "34",
        )

        val path = listOf(
            HeatNode(Point2D(1, 1)),
            HeatNode(Point2D(1, 0)),
            HeatNode(Point2D(0, 0))
        )

        // act
        val heatLoss = Day17().sumHeatLoss(heatMap, path)

        // assert
        assertThat(heatLoss).isEqualTo(6)

    }
}