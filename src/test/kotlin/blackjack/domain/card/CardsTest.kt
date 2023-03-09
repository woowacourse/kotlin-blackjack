package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드 목록에 카드를 추가한다`() {
        val cards = Cards()

        cards.add(Card(CardNumber.ACE, Suit.SPADE))
        cards.add(Card(CardNumber.THREE, Suit.SPADE))
        cards.add(Card(CardNumber.FIVE, Suit.SPADE))

        assertThat(cards.items.size).isEqualTo(3)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점 이하이면 11점으로 계산한다`() {
        val cards = Cards()
        cards.add(Card(CardNumber.ACE, Suit.SPADE))

        assertThat(cards.calculateTotalScore()).isEqualTo(11)
    }

    @Test
    fun `A는 기존 총 점수에 11점을 더한 값이 21점을 초과하면 1점으로 계산한다`() {
        val cards = Cards()
        cards.add(Card(CardNumber.ACE, Suit.SPADE))
        cards.add(Card(CardNumber.ACE, Suit.HEART))

        assertThat(cards.calculateTotalScore()).isEqualTo(12)
    }
}
