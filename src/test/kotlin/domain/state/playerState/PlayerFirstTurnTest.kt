package domain.state.playerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.state.BlackJack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerFirstTurnTest {

    @Test
    fun `PlayerFirstTurn 에서 FirstTurn 된다`() {
        val actual = PlayerFirstTurn(Hand()).draw(Card(CardShape.DIAMOND, CardNumber.KING))

        assertThat(actual is PlayerFirstTurn).isTrue
    }

    @Test
    fun `PlayerFirstTurn 에서 Hit 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.TEN))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.KING))

        assertThat(actual is PlayerHit).isTrue
    }

    @Test
    fun `PlayerFirstTurn 에서 BlackJack 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.KING))

        assertThat(actual is BlackJack).isTrue
    }
}
