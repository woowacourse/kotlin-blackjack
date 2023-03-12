package domain.state.dealerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.money.Money
import domain.money.Profit
import domain.state.playerState.PlayerFirstTurn
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerBlackJackTest {
    @Test
    fun `BlackJack 상태일 때 상대가 블랙잭이 아니면 배당률은 1 배 이다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val actual = DealerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        val otherHand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val other = PlayerFirstTurn(otherHand).draw(Card(CardShape.DIAMOND, CardNumber.TWO)).stay()

        Assertions.assertThat(actual.profit(other, Money(10000))).isEqualTo(Profit(10000.0))
    }
}
