package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `끗수와 슈트 조합이 같은 Card는 서로 동등하다`() {
        val card1 = Card(CardNumber.ACE, Suit.CLOVER)
        val card2 = Card(CardNumber.ACE, Suit.CLOVER)
        assertThat(card1).isEqualTo(card2)
    }
}
