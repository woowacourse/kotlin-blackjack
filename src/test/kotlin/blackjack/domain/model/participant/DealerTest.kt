package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 첫 손패를 보여줄 수 있다`() {
        val dealerHand =
            Hand.of(
                Card(CardNumber.ACE, Suit.HEART),
                Card(CardNumber.QUEEN, Suit.CLUB),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualFirstCard = dealer.showFirstCard()

        val expectedCard = Card(CardNumber.ACE, Suit.HEART)

        assertThat(actualFirstCard).isEqualTo(expectedCard)
    }

    @Test
    fun `플레이어와 비교해서 승패 결과를 가져올 수 있고, 둘 다 버스트인 경우 딜러가 승리한다`() {
        val playerHand =
            Hand.of(
                Card(CardNumber.KING, Suit.SPADE),
                Card(CardNumber.QUEEN, Suit.CLUB),
                Card(CardNumber.TWO, Suit.HEART),
            )
        val player = Player("크림", playerHand)
        val dealerHand =
            Hand.of(
                Card(CardNumber.JACK, Suit.SPADE),
                Card(CardNumber.FIVE, Suit.CLUB),
                Card(CardNumber.TEN, Suit.DIAMOND),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = dealer.compareTo(player)

        val expectedResult = GameResult.WIN

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어와 비교해서 승패 결과를 가져올 수 있고, 둘 다 버스트가 아닌 경우 점수로 비교한다`() {
        val playerHand =
            Hand.of(
                Card(CardNumber.KING, Suit.SPADE),
                Card(CardNumber.QUEEN, Suit.CLUB),
                Card(CardNumber.ACE, Suit.HEART),
            )
        val player = Player("크림", playerHand)

        val dealerHand =
            Hand.of(
                Card(CardNumber.JACK, Suit.SPADE),
                Card(CardNumber.FIVE, Suit.CLUB),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = dealer.compareTo(player)

        val expectedResult = GameResult.LOSE

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `딜러 카드의 총합이 16보다 작으면 카드를 더 받을 수 있다`() {
        val dealerHand =
            Hand.of(
                Card(CardNumber.KING, Suit.SPADE),
                Card(CardNumber.SIX, Suit.CLUB),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualIsDrawable = dealer.isDrawable()

        val expected = true

        assertThat(actualIsDrawable).isEqualTo(expected)
    }

    @Test
    fun `딜러 카드의 총합이 17 이상이면 카드를 더 받을 수 있다`() {
        val dealerHand =
            Hand.of(
                Card(CardNumber.KING, Suit.SPADE),
                Card(CardNumber.SEVEN, Suit.CLUB),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualIsDrawable = dealer.isDrawable()

        val expected = false

        assertThat(actualIsDrawable).isEqualTo(expected)
    }
}
