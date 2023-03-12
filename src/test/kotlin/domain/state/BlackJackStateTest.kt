package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import state.BlackJackState
import state.BustState
import state.StayState

class BlackJackStateTest {
    @Test
    fun `블랙잭이 아닌 카드로는 BlackJackState를 생성할 수 없다`() {
        assertThrows<IllegalStateException> {
            BlackJackState(
                Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                Card.of(CardCategory.DIAMOND, CardNumber.ACE)
            )
        }
    }

    @Test
    fun `블랙잭 상태가 된다면 next를 호출하면 예외가 발생한다`() {
        val blackJackState = BlackJackState(
            Card.of(CardCategory.CLOVER, CardNumber.ACE),
            Card.of(CardCategory.CLOVER, CardNumber.JACK)
        )
        assertThrows<IllegalStateException> { blackJackState.draw(Card.of(CardCategory.CLOVER, CardNumber.NINE)) }
    }

    @Test
    fun `딜러도 블랙잭상태이면 수익률은 0이다`() {
        val blackJackState = BlackJackState(
            Card.of(CardCategory.CLOVER, CardNumber.ACE),
            Card.of(CardCategory.CLOVER, CardNumber.JACK)
        )
        val dealerState = BlackJackState(
            Card.of(CardCategory.CLOVER, CardNumber.ACE),
            Card.of(CardCategory.CLOVER, CardNumber.JACK)
        )
        val actual = blackJackState.resultProfit(dealerState)
        val expected = 0.0
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러가 블랙잭이 아니면 수익률은 1'5다`() {
        val blackJackState = BlackJackState(
            Card.of(CardCategory.CLOVER, CardNumber.ACE),
            Card.of(CardCategory.CLOVER, CardNumber.JACK)
        )

        assertAll(
            "플레이어가 블랙잭인데 딜러가 블랙잭이 아니면 플레이어가 이긴다",
            {
                // 딜러가 21이지만 카드가 세장이라서 블랙잭이 아닌 경우
                val dealerState = StayState(
                    Card.of(CardCategory.CLOVER, CardNumber.FIVE),
                    Card.of(CardCategory.CLOVER, CardNumber.JACK),
                    Card.of(CardCategory.CLOVER, CardNumber.SIX)
                )
                val actual = blackJackState.resultProfit(dealerState)
                val expected = 1.5
                assertThat(actual).isEqualTo(expected)
            },
            {
                // 딜러가 버스트인 경우
                val dealerState = BustState(
                    Card.of(CardCategory.CLOVER, CardNumber.FIVE),
                    Card.of(CardCategory.CLOVER, CardNumber.JACK),
                    Card.of(CardCategory.CLOVER, CardNumber.TEN)
                )
                val actual = blackJackState.resultProfit(dealerState)
                val expected = 1.5
                assertThat(actual).isEqualTo(expected)
            }
        )
    }
}
