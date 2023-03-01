package blackjack.domain

import blackjack.domain.CardMark.CLOVER
import blackjack.domain.CardValue.ACE
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

    @Test
    fun `ACE 카드가 있는지 확인 할 수 있다`() {
        val cards = Cards()
        cards.add(Card(CLOVER, EIGHT))
        assertThat(cards.containsACE()).isFalse
        cards.add(Card(CLOVER, ACE))
        assertThat(cards.containsACE()).isTrue
    }
}
