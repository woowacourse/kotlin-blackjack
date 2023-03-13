package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import domain.card.Cards
import domain.participant.BettingMoney
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BurstTest {
    @Test
    fun `버스트 상태에서 카드를 더 뽑을 수 없다`() {
        // given
        val state = Burst(Cards(10, 10, 10), BettingMoney(1000))
        // when
        // then
        assertThrows<IllegalStateException> { state.draw(Card(1)) }
    }

    @Test
    fun `수익률을 계산하면 딜러의 상태와 상관없이 -1 이다`() {
        // given
        val state = Burst(Cards(10, 10, 10), BettingMoney(1000))
        val dealerState = Stay(Cards(1, 2), BettingMoney(0))
        // when
        val actual = state.getProfitRate(dealerState)
        // then
        assertThat(actual).isEqualTo(-1000.0)
    }

    private fun Card(number: Int): Card {
        return Card.of(CardCategory.CLOVER, CardNumber.values().find { it.value == number } ?: CardNumber.FIVE)
    }

    private fun Cards(vararg cards: Int): Cards {
        return Cards(
            cards.map { number ->
                Card(number)
            },
        )
    }
}
