package blackjack.domain.model.participant

import blackjack.domain.model.GameResult
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardNumber
import blackjack.domain.model.card.Hand
import blackjack.domain.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어가 블랙잭이고 딜러가 18점이면 플레이어가 블랙잭 승리한다`() {
        val playerHand =
            Hand.of(
                Card.of(CardNumber.KING, Suit.SPADE),
                Card.of(CardNumber.ACE, Suit.HEART),
            )
        val player = Player("크림", playerHand)
        val dealerHand =
            Hand.of(
                Card.of(CardNumber.JACK, Suit.SPADE),
                Card.of(CardNumber.EIGHT, Suit.CLUB),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = player.compareTo(dealer)

        val expectedResult = GameResult.BLACKJACK_WIN

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어가 12점이고 딜러가 버스트이면 플레이어가 승리한다`() {
        val playerHand =
            Hand.of(
                Card.of(CardNumber.KING, Suit.SPADE),
                Card.of(CardNumber.TWO, Suit.HEART),
            )
        val player = Player("크림", playerHand)
        val dealerHand =
            Hand.of(
                Card.of(CardNumber.JACK, Suit.SPADE),
                Card.of(CardNumber.EIGHT, Suit.CLUB),
                Card.of(CardNumber.QUEEN, Suit.DIAMOND),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = player.compareTo(dealer)

        val expectedResult = GameResult.WIN

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어가 버스트고 딜러가 17점이면 플레이어가 패배한다`() {
        val playerHand =
            Hand.of(
                Card.of(CardNumber.KING, Suit.SPADE),
                Card.of(CardNumber.QUEEN, Suit.HEART),
                Card.of(CardNumber.JACK, Suit.DIAMOND),
            )
        val player = Player("크림", playerHand)
        val dealerHand =
            Hand.of(
                Card.of(CardNumber.JACK, Suit.SPADE),
                Card.of(CardNumber.SEVEN, Suit.CLUB),
            )
        val dealer = Dealer("딜러", dealerHand)

        val actualResult = player.compareTo(dealer)

        val expectedResult = GameResult.LOSE

        assertThat(actualResult).isEqualTo(expectedResult)
    }

    @Test
    fun `플레이어가 드로우를 더 할 수 있는지 여부를 알 수 있다`() {
        val playerHand =
            Hand.of(
                Card.of(CardNumber.TEN, Suit.SPADE),
                Card.of(CardNumber.TEN, Suit.HEART),
                Card.of(CardNumber.TWO, Suit.CLUB),
            )
        val player = Player("크림", playerHand)
        val actualIsDrawable = player.isDrawable()

        val expectedIsDrawable = false

        assertThat(actualIsDrawable).isEqualTo(expectedIsDrawable)
    }
}
