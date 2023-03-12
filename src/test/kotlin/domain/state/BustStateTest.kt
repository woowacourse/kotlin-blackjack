package domain.state

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import state.BlackJackState
import state.BustState
import state.StayState

class BustStateTest {
    @Test
    fun `카드가 버스트가 아닌 경우 BustState를 생성할 수 없다`() {
        assertThrows<IllegalStateException> {
            BustState(
                Card.of(CardCategory.CLOVER, CardNumber.JACK),
                Card.of(CardCategory.DIAMOND, CardNumber.ACE),
                Card.of(CardCategory.CLOVER, CardNumber.JACK)
            )
        }
    }

    @Test
    fun `플레이어가 버스트라면 딜러가 무엇이든지 수익률은 -1이다`() {
        val bustState = BustState(
            Card.of(CardCategory.CLOVER, CardNumber.TWO),
            Card.of(CardCategory.CLOVER, CardNumber.JACK),
            Card.of(CardCategory.CLOVER, CardNumber.TEN)
        )

        val expected = -1.0

        assertAll(
            "플레이어가 버스트이면 딜러가 이긴다",
            {
                // 딜러가 stay인 경우
                val dealerState = StayState(
                    Card.of(CardCategory.CLOVER, CardNumber.FIVE),
                    Card.of(CardCategory.CLOVER, CardNumber.JACK),
                    Card.of(CardCategory.CLOVER, CardNumber.SIX)
                )
                val actual = bustState.resultProfit(dealerState)
                Assertions.assertThat(actual).isEqualTo(expected)
            },
            {
                // 딜러가 블랙잭인 경우
                val dealerState = BlackJackState(
                    Card.of(CardCategory.CLOVER, CardNumber.ACE),
                    Card.of(CardCategory.CLOVER, CardNumber.JACK)
                )
                val actual = bustState.resultProfit(dealerState)
                Assertions.assertThat(actual).isEqualTo(expected)
            },
            {
                // 딜러가 버스트인 경우
                val dealerState = BustState(
                    Card.of(CardCategory.CLOVER, CardNumber.FIVE),
                    Card.of(CardCategory.CLOVER, CardNumber.JACK),
                    Card.of(CardCategory.CLOVER, CardNumber.TEN)
                )
                val actual = bustState.resultProfit(dealerState)
                Assertions.assertThat(actual).isEqualTo(expected)
            }
        )
    }
}
