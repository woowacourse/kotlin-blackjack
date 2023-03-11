package domain.state.playerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.state.Bust
import domain.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerHitTest {
    @Test
    fun `Hit 에서 Bust 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.TWO), Card(CardShape.HEART, CardNumber.KING))
        val actual =
            PlayerHit(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Hit 에서 Hit 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.TWO), Card(CardShape.DIAMOND, CardNumber.TEN))
        val actual =
            PlayerHit(hand).draw(Card(CardShape.HEART, CardNumber.NINE))

        assertThat(actual).isInstanceOf(PlayerHit::class.java)
    }

    @Test
    fun `Hit 에서 Stay 상태가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.TWO), Card(CardShape.DIAMOND, CardNumber.TEN))
        val actual =
            PlayerHit(hand).stay()

        assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
