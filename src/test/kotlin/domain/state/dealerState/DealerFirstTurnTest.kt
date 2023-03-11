package domain.state.dealerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.state.BlackJack
import domain.state.playerState.PlayerFirstTurn
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerFirstTurnTest {
    @Test
    fun `DealerFirstTurn 에서 FirstTurn 이 된다`() {
        val actual = DealerFirstTurn(Hand()).draw(Card(CardShape.DIAMOND, CardNumber.KING))

        Assertions.assertThat(actual).isInstanceOf(DealerFirstTurn::class.java)
    }

    @Test
    fun `DealerFirstTurn 에서 Hit 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.SIX))
        val actual = DealerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.KING))

        Assertions.assertThat(actual).isInstanceOf(DealerHit::class.java)
    }

    @Test
    fun `DealerFirstTurn 에서 BlackJack 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.KING))

        Assertions.assertThat(actual).isInstanceOf(BlackJack::class.java)
    }
}
