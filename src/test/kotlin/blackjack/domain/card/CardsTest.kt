package blackjack.domain.card

import blackjack.domain.card.CardMark.CLOVER
import blackjack.domain.card.CardValue.ACE
import blackjack.domain.card.CardValue.EIGHT
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드를 추가할 수 있다`() {
        var cards = Cards()
        cards += Card(CLOVER, EIGHT)
        assertThat(cards.size).isEqualTo(1)
        assertThat(cards.toList()[0]).isEqualTo(Card(CLOVER, EIGHT))
    }

    @Test
    fun `ACE 카드가 있는지 확인 할 수 있다`() {
        var cards = Cards()
        cards += Card(CLOVER, EIGHT)
        assertThat(cards.containsACE()).isFalse
        cards += Card(CLOVER, ACE)
        assertThat(cards.containsACE()).isTrue
    }
}
