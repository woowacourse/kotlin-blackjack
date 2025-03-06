package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Ace : Rank {
    override val possibleValues: List<Int> = ACE_VALUES

    companion object {
        val ACE_VALUES = listOf(1, 11)
    }
}

class AceTest {
    @Test
    fun `Ace는 1 또는 11로 계산된다`() {
        val ace = Ace()
        assertThat(ace.possibleValues).contains(1)
        assertThat(ace.possibleValues).contains(11)
    }
}
