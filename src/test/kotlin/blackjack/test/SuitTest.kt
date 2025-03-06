package blackjack.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

enum class Suit {
    SPADE,
    HEART,
    DIAMOND,
    CLOVER,
}

class SuitTest {
    @Test
    fun `수트의 종류는 스페이드(♤, Spade), 하트(♡, Heart), 다이아몬드(◇, Diamond), 클로버(♧, Clover)가 있다`() {
        assertThat(Suit.entries).isEqualTo(listOf(Suit.SPADE, Suit.HEART, Suit.DIAMOND, Suit.CLOVER))
    }
}
