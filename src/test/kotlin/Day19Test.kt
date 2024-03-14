import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day19Test {

    @Test
    fun should_load_workflows() {

        // act
        val workflows = Day19().loadWorkflows(Paths.get("src", "test", "resources", "Day19_TestData.txt"))

        // assert
        assertThat(workflows).hasSize(11)
    }

    @Test
    fun should_load_parts() {
        // act
        val parts = Day19().loadParts(Paths.get("src", "test", "resources", "Day19_TestData.txt"))

        // assert
        assertThat(parts).hasSize(5)
    }

    @Test
    fun should_return_key_of_first_rule_in_workflow() {

        // act
        val key = Workflow("in{s<1351:px,qqz}").next(Part("{x=787,m=2655,a=1222,s=100}"))

        // assert
        assertThat(key).isEqualTo("px")
    }

    @Test
    fun should_return_key_of_last_rule_in_workflow() {

        // act
        val key = Workflow("in{s<1351:px,qqz}").next(Part("{x=787,m=2655,a=1222,s=2000}"))

        // assert
        assertThat(key).isEqualTo("qqz")
    }

    @Test
    fun should_create_part() {

        // act
        val part = Part("{x=787,m=2655,a=1222,s=2876}")

        // assert
        assertThat(part.x).isEqualTo(787)
        assertThat(part.m).isEqualTo(2655)
        assertThat(part.a).isEqualTo(1222)
        assertThat(part.s).isEqualTo(2876)
    }

    @Test
    fun should_create_workflow() {
        // arrange

        // act
        val workflow = Workflow("qqz{s>2770:qs,m<1801:hdj,R}")

        // assert
        assertThat(workflow.key).isEqualTo("qqz")
        assertThat(workflow.rules).contains(
            Rule("s>2770:qs"),
            Rule("m<1801:hdj"),
            Rule("R"),
        )
    }

    @Test
    fun should_create_rule_with_field() {
        // act
        val rule = Rule("s>2770:qs")

        // assert
        assertThat(rule.field).isEqualTo("s")
        assertThat(rule.operator).isEqualTo('>')
        assertThat(rule.value).isEqualTo(2770)
        assertThat(rule.next).isEqualTo("qs")
    }

    @Test
    fun should_create_rule_with_next() {
        // act
        val rule = Rule("srfg")

        // assert
        assertThat(rule.field).isNull()
        assertThat(rule.operator).isNull()
        assertThat(rule.value).isNull()
        assertThat(rule.next).isEqualTo("srfg")
    }

}