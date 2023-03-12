package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import domain.card.Cards
import domain.participant.BettingMoney
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackJackTest {
    @Test
    fun `블랙잭상태에서 카드를 더 뽑을 수 없다`() {
        // given
        val state = BlackJack(Cards(1, 10), BettingMoney(1000))
        // when
        // then
        assertThrows<IllegalStateException> { state.draw(Card(1)) }
    }

    @Test
    fun `플레이어는 블랙잭이고 딜러가 블랙잭이 아니라면 수익률은 2분의3배 이다`() {
        // given
        val state = BlackJack(Cards(1, 10), BettingMoney(1000))
        val dealerState = Stay(Cards(1, 2), BettingMoney(0))
        // when
        val actual = state.getProfitRate(dealerState)
        // then
        Assertions.assertThat(actual).isEqualTo(1500.0)
    }

    @Test
    fun `플레이어는 블랙잭이고 딜러도 블랙잭이라면 수익률은 0 이다`() {
        // given
        val state = BlackJack(Cards(1, 10), BettingMoney(1000))
        val dealerState = BlackJack(Cards(1, 10), BettingMoney(0))
        // when
        val actual = state.getProfitRate(dealerState)
        // then
        Assertions.assertThat(actual).isEqualTo(0.0)
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
