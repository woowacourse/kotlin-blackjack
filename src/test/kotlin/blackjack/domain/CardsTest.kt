package blackjack.domain

import blackjack.domain.CardMark.CLOVER
import blackjack.domain.CardValue.EIGHT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        val cards = Cards()
        cards.add(Card(CLOVER, EIGHT))
        assertThat(cards.size).isEqualTo(1)
        assertThat(cards.toList()[0]).isEqualTo(Card(CLOVER, EIGHT))
    }
}
