package dsl

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

class SkillsTest {
    @Test
    fun name() {
        val numbers = mutableListOf("1","2","3")
        val (a1, a2, a3) = numbers
        val skills = Skills(numbers)
        numbers.add("4")
        assertThat(skills.skills).hasSize(3)
//        assertThat(a1).isEqualTo(1)
//        assertThat(a2).isEqualTo(2)
//        assertThat(a3).isEqualTo(3)

        val mlist = mutableListOf<Int>()

        numbers.get(1)
        numbers[1]

        val wPair = WPair(1, 2)
        wPair[1, 2]
    }

    class WPair(val a: Int, val b: Int) {
        operator fun get(i: Int, j: Int): Pair<Int, Int> {
            return a to b
        }
    }
}
