package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `딜러와 비교해서 승패 결과를 가져올 수 있다`() {
        val player = Player()
        player.handCards.add(Card(CardNumber.KING, Suit.SPADE))
        player.handCards.add(Card(CardNumber.ACE, Suit.HEART))

        val dealer = Dealer()
        dealer.handCards.add(Card(CardNumber.JACK, Suit.SPADE))
        dealer.handCards.add(Card(CardNumber.EIGHT, Suit.CLUB))

        val result = player.compareTo(dealer)
        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어가 드로우를 더 할 수 있는지 여부를 알 수 있다`() {
        val player = Player()
        player.handCards.add(Card(CardNumber.TEN, Suit.SPADE))
        player.handCards.add(Card(CardNumber.TEN, Suit.HEART))
        player.handCards.add(Card(CardNumber.TWO, Suit.CLUB))

        val isDrawable = player.isDrawable()

        assertThat(isDrawable).isEqualTo(false)
    }
}
