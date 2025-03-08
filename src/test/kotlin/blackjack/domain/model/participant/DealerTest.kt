package blackjack.domain.model.participant

import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 첫 손패를 보여줄 수 있다`() {
        val dealer = Dealer()
        dealer.handCards.add(Card(CardNumber.ACE, Suit.HEART))
        dealer.handCards.add(Card(CardNumber.QUEEN, Suit.CLUB))

        val firstCardOfDealer = dealer.showFirstCard()

        assertThat(firstCardOfDealer).isEqualTo(Card(CardNumber.ACE, Suit.HEART))
    }

    @Test
    fun `딜러가 드로우를 더 할 수 있는지 여부를 알 수 있다`() {
        val dealer = Dealer()
        dealer.handCards.add(Card(CardNumber.KING, Suit.SPADE))
        dealer.handCards.add(Card(CardNumber.SEVEN, Suit.CLUB))

        val isDrawable = dealer.isDrawable()

        assertThat(isDrawable).isEqualTo(false)
    }
}
