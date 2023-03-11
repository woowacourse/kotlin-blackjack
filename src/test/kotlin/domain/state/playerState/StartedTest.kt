package domain.state.playerState

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.money.Money
import domain.state.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StartedTest {
    @Test
    fun `카드들을 반환한다`() {
        val hand = Hand(
            Card(CardShape.HEART, CardNumber.KING),
        )
        val actual = PlayerFirstTurn(hand)
            .draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThat(actual.getHandCards()).isEqualTo(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.DIAMOND, CardNumber.TEN),
            ),
        )
    }

    @Test
    fun `게임 진행 중에 수익률을 계산할 수 없다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val actual = PlayerFirstTurn(hand)
            .draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThrows<IllegalStateException> { actual.profit(Money(10000)) }
    }

    @Test
    fun `Started 에서 Stay 가 된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val actual = PlayerFirstTurn(hand)
            .draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThat(actual.stay()).isInstanceOf(Stay::class.java)
    }
}
