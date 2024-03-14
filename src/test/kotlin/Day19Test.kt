import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.nio.file.Paths

class Day19Test {

    private val workflows = mapOf(
        "px" to Workflow("px{a<2006:qkq,m>2090:A,rfg}"),
        "pv" to Workflow("pv{a>1716:R,A}"),
        "lnx" to Workflow("lnx{m>1548:A,A}"),
        "rfg" to Workflow("rfg{s<537:gd,x>2440:R,A}"),
        "qs" to Workflow("qs{s>3448:A,lnx}"),
        "qkq" to Workflow("qkq{x<1416:A,crn}"),
        "crn" to Workflow("crn{x>2662:A,R}"),
        "in" to Workflow("in{s<1351:px,qqz}"),
        "qqz" to Workflow("qqz{s>2770:qs,m<1801:hdj,R}"),
        "gd" to Workflow("gd{a>3333:R,R}"),
        "hdj" to Workflow("hdj{m>838:A,pv}"),
    )

    private val part1 = Part("{x=2127,m=1623,a=2188,s=1013}")
    private val part2 = Part("{x=2461,m=1339,a=466,s=291}")
    private val part3 = Part("{x=2036,m=264,a=79,s=2244}")
    private val part4 = Part("{x=1679,m=44,a=2067,s=496}")
    private val part5 = Part("{x=787,m=2655,a=1222,s=2876}")


    private val parts = listOf(part1, part2, part3, part4, part5)

    @Test
    fun should_load_workflows() {

        // act
        val workflows = Day19().loadWorkflows(Paths.get("src", "test", "resources", "Day19_TestData.txt"))

        // assert
        assertThat(workflows).hasSize(11)
        assertThat(workflows.keys).containsExactly(
            "px",
            "pv",
            "lnx",
            "rfg",
            "qs",
            "qkq",
            "crn",
            "in",
            "qqz",
            "gd",
            "hdj",
        )
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

    @Test
    fun should_run_workflows_with_part1() {
        // act
        val result = Day19().runWorkflows(workflows, part1)

        // assert
        // in -> qqz -> qs -> lnx -> A
        assertThat(result).isEqualTo("A")
    }

    @Test
    fun should_run_workflows_with_part2() {
        // act
        val result = Day19().runWorkflows(workflows, part2)

        // assert
        // in -> px -> rfg -> gd -> R
        assertThat(result).isEqualTo("R")
    }

    @Test
    fun should_run_workflows_with_part3() {
        // act
        val result = Day19().runWorkflows(workflows, part3)

        // assert
        // in -> qqz -> hdj -> pv -> A
        assertThat(result).isEqualTo("A")
    }

    @Test
    fun should_run_workflows_with_part4() {
        // act
        val result = Day19().runWorkflows(workflows, part4)

        // assert
        // in -> px -> qkq -> crn -> R
        assertThat(result).isEqualTo("R")
    }

    @Test
    fun should_run_workflows_with_part5() {
        // arrange
        val part5 = Part("{x=2127,m=1623,a=2188,s=1013}")

        // act
        val result = Day19().runWorkflows(workflows, part5)

        // assert
        // in -> px -> rfg -> A
        assertThat(result).isEqualTo("A")
    }

    @Test
    fun should_run_workflows_for_all_parts() {

        // act
        val result = Day19().runWorkflowsAll(workflows, parts)

        // assert
        assertThat(result).containsExactly(part1, part3, part5)
    }
}