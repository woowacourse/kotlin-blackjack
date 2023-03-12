package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import domain.card.Cards
import domain.participant.BettingMoney
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class StayTest {
    @Test
    fun `Stay 상태에서 카드를 더 뽑을 수 없다`() {
        // given
        val state = Stay(Cards(5, 10, 5), BettingMoney(1000))
        // when
        // then
        assertThrows<IllegalStateException> { state.draw(Card(1)) }
    }

    @Test
    fun `딜러가 burst라면 수익률은 1 이다`() {
        // given
        val state = Stay(Cards(10, 10), BettingMoney(1000))
        val dealerState = Burst(Cards(1, 2), BettingMoney(0))
        // when
        val actual = state.getProfitRate(dealerState)
        // then
        Assertions.assertThat(actual).isEqualTo(1000.0)
    }

    @Test
    fun `딜러가 BlackJack 이라면 수익률은 -1 이다`() {
        // given
        val state = Stay(Cards(10, 10), BettingMoney(1000))
        val dealerState = BlackJack(Cards(1, 10), BettingMoney(0))
        // when
        val actual = state.getProfitRate(dealerState)
        // then
        Assertions.assertThat(actual).isEqualTo(-1000.0)
    }

    @Test
    fun `둘다 Stay이고 딜러 점수가 더 높으면 이라면 수익률은 -1 이다`() {
        // given
        val state = Stay(Cards(2, 10), BettingMoney(1000))
        val dealerState = Stay(Cards(5, 10), BettingMoney(0))
        // when
        val actual = state.getProfitRate(dealerState)
        // then
        Assertions.assertThat(actual).isEqualTo(-1000.0)
    }

    @Test
    fun `둘다 Stay이고 플레이어 점수가 더 높으면 이라면 수익률은 1 이다`() {
        // given
        val state = Stay(Cards(6, 10), BettingMoney(1000))
        val dealerState = Stay(Cards(5, 10), BettingMoney(0))
        // when
        val actual = state.getProfitRate(dealerState)
        // then
        Assertions.assertThat(actual).isEqualTo(1000.0)
    }

    @Test
    fun `둘다 Stay이고 점수가 같으면 수익률은 0 이다`() {
        // given
        val state = Stay(Cards(5, 10), BettingMoney(1000))
        val dealerState = Stay(Cards(5, 10), BettingMoney(0))
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
