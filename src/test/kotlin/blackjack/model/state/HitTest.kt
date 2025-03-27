package blackjack.model.state

import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.card.Denomination
import blackjack.model.card.Hand
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `카드를 추가했는 데, 점수가 21점미만이면 Hit을 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.TWO), Card(CardShape.HEART, Denomination.ACE))
        val card = Card(CardShape.HEART, Denomination.THREE)
        val hit = Hit(hand)

        val actual = hit.draw(card)

        Assertions.assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드를 추가했는 데, 카드가 2장이고 점수가 21점이면 Blackjack을 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE))
        val card = Card(CardShape.HEART, Denomination.TEN)
        val hit = Hit(hand)

        val actual = hit.draw(card)

        Assertions.assertThat(actual).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `카드를 추가했는 데, 점수가 21점이면 Stay을 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE), Card(CardShape.SPADE, Denomination.TWO))
        val card = Card(CardShape.HEART, Denomination.EIGHT)
        val hit = Hit(hand)

        val actual = hit.draw(card)

        Assertions.assertThat(actual).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `카드를 추가했는 데, 점수가 21점초과하면 Bust를 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.TEN), Card(CardShape.SPADE, Denomination.TEN))
        val card = Card(CardShape.HEART, Denomination.EIGHT)
        val hit = Hit(hand)

        val actual = hit.draw(card)

        Assertions.assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드를 추가 하지 않으면 Stay을 반환한다`() {
        val hand = Hand(Card(CardShape.HEART, Denomination.ACE), Card(CardShape.SPADE, Denomination.TWO))

        val actual = Hit(hand).stop()

        Assertions.assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
