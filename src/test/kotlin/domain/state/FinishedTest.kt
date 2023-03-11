package domain.state

import domain.card.Card
import domain.card.CardNumber
import domain.card.CardShape
import domain.card.Hand
import domain.money.Money
import domain.state.playerState.PlayerFirstTurn
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FinishedTest {

    @Test
    fun `Stay 에서 draw 하면 안된다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThrows<IllegalStateException> { actual.draw(Card(CardShape.HEART, CardNumber.NINE)) }
    }

    @Test
    fun `BlackJack 상태일 때 배당률은 일점오배 이다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.TEN))

        assertThat(actual.profit(Money(10000))).isEqualTo(15000.0)
    }

    @Test
    fun `Stay 상태일 때 배당률은 1배 이다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.ACE))
        val actual = PlayerFirstTurn(hand).draw(Card(CardShape.DIAMOND, CardNumber.NINE)).stay()

        assertThat(actual.profit(Money(10000))).isEqualTo(10000.0)
    }

    @Test
    fun `Bust 상태일 때 배당률은 -1배 이다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val actual = PlayerFirstTurn(hand)
            .draw(Card(CardShape.DIAMOND, CardNumber.TEN))
            .draw(Card(CardShape.DIAMOND, CardNumber.QUEEN))

        assertThat(actual.profit(Money(10000))).isEqualTo(-10000.0)
    }

    @Test
    fun `게임이 끝난 상태에서 stay 할 수 없다`() {
        val hand = Hand(Card(CardShape.HEART, CardNumber.KING))
        val actual = PlayerFirstTurn(hand)
            .draw(Card(CardShape.DIAMOND, CardNumber.TEN))
            .draw(Card(CardShape.DIAMOND, CardNumber.QUEEN))

        assertThrows<IllegalStateException> { actual.stay() }
    }

    @Test
    fun `카드들을 반환한다`() {
        val hand = Hand(
            Card(CardShape.HEART, CardNumber.KING),
        )
        val actual = PlayerFirstTurn(hand)
            .draw(Card(CardShape.DIAMOND, CardNumber.TEN))
            .draw(Card(CardShape.DIAMOND, CardNumber.QUEEN))

        assertThat(actual.getHandCards()).isEqualTo(
            listOf(
                Card(CardShape.HEART, CardNumber.KING),
                Card(CardShape.DIAMOND, CardNumber.TEN),
                Card(CardShape.DIAMOND, CardNumber.QUEEN),
            ),
        )
    }
}
