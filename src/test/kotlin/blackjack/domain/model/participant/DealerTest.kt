package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
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
    fun `플레이어와 비교해서 승패 결과를 가져올 수 있고, 둘 다 버스트인 경우 딜러가 승리한다`() {
        val player = Player()
        player.handCards.add(Card(CardNumber.KING, Suit.SPADE))
        player.handCards.add(Card(CardNumber.QUEEN, Suit.CLUB))
        player.handCards.add(Card(CardNumber.TWO, Suit.HEART))

        val dealer = Dealer()
        dealer.handCards.add(Card(CardNumber.JACK, Suit.SPADE))
        dealer.handCards.add(Card(CardNumber.FIVE, Suit.CLUB))
        dealer.handCards.add(Card(CardNumber.TEN, Suit.DIAMOND))

        val result = dealer.compareTo(player)
        assertThat(result).isEqualTo(GameResult.WIN)
    }

    @Test
    fun `플레이어와 비교해서 승패 결과를 가져올 수 있고, 둘 다 버스트가 아닌 경우 점수로 비교한다`() {
        val player = Player()
        player.handCards.add(Card(CardNumber.KING, Suit.SPADE))
        player.handCards.add(Card(CardNumber.QUEEN, Suit.CLUB))
        player.handCards.add(Card(CardNumber.ACE, Suit.HEART))

        val dealer = Dealer()
        dealer.handCards.add(Card(CardNumber.JACK, Suit.SPADE))
        dealer.handCards.add(Card(CardNumber.FIVE, Suit.CLUB))

        val result = dealer.compareTo(player)
        assertThat(result).isEqualTo(GameResult.LOSE)
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
