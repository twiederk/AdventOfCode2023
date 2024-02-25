import Point2D.Companion.EAST
import Point2D.Companion.SOUTH
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
    fun should_solve_part_2_with_A_Star_Algorithm() {

        // act
        val heatLoss = Day17().part2(heatMap)

        // assert
        assertThat(heatLoss).isEqualTo(94)
    }

    @Test
    fun should_expand_two_neighbor_nodes_when_current_is_upper_left_corner() {
        // arrange
        val currentHeatNode = HeatNode(Point2D(0, 0))

        // act
        val neighbors =
            Day17().expandNode(heatMap, currentHeatNode, emptySet(), emptyList(), HeatNode::isValidMovePart1)

        // assert
        assertThat(neighbors).hasSize(2)
    }

    @Test
    fun should_expand_one_neighbor_when_one_is_already_in_closed_list() {
        // arrange
        val currentHeatNode = HeatNode(Point2D(0, 0))
        val closedList = setOf(HeatNode(Point2D(0, 1), SOUTH, 1))

        // act
        val neighbors =
            Day17().expandNode(heatMap, currentHeatNode, closedList, emptyList(), HeatNode::isValidMovePart1)

        // assert
        assertThat(neighbors).hasSize(1)
    }

    @Test
    fun should_expand_one_neighbor_when_one_is_in_open_list_with_better_way() {
        // arrange
        val currentHeatNode = HeatNode(Point2D(0, 0))
        val openList = listOf(HeatNode(Point2D(0, 1), SOUTH, 1))

        // act
        val neighbors = Day17().expandNode(heatMap, currentHeatNode, emptySet(), openList, HeatNode::isValidMovePart1)

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
        assertThat(heatLoss).isEqualTo(7)

    }

    @Test
    fun should_solve_with_a_start_when_simple_map_is_given() {
        // arrange
        val heatMap = listOf(
            "123",
            "456",
            "789",
        )

        // act
        val path = Day17().aStar(heatMap, HeatNode::isValidMovePart1)

        // assert
        assertThat(path).containsExactly(
            HeatNode(coords = Point2D(x = 1, y = 0), EAST, steps = 1),
            HeatNode(coords = Point2D(x = 2, y = 0), EAST, steps = 2),
            HeatNode(coords = Point2D(x = 2, y = 1), SOUTH, steps = 1),
            HeatNode(coords = Point2D(x = 2, y = 2), SOUTH, steps = 2)
        )
    }

    @Test
    fun should_find_path_when_closed_list_is_given() {
        // arrange
        val step1 = HeatNode(coords = Point2D(x = 1, y = 0), EAST, steps = 1)
        val step2 = HeatNode(coords = Point2D(x = 2, y = 0), EAST, steps = 2).apply { parent = step1 }
        val step3 = HeatNode(coords = Point2D(x = 2, y = 1), SOUTH, steps = 1).apply { parent = step2 }
        val end = HeatNode(coords = Point2D(x = 2, y = 2), SOUTH, steps = 2).apply { parent = step3 }

        val closedList = listOf(
            end, step1, step2, step3
        )

        // act
        val path = Day17().getPath(end, closedList)

        // assert
        assertThat(path).containsExactly(step1, step2, step3, end)
    }

}