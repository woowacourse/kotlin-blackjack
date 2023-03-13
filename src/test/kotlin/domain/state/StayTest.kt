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
import state.RateOfProfit
import state.StayState

class StayTest {
    @Test
    fun `블랙잭이거나 버스트이면 Stay상태가 될 수 없다`() {
        assertAll(
            "카드가 두 장 미만이고 블랙잭이거나 버스트이면 예외가 발생한다.",
            {
                // 카드가 한장인 경우
                assertThrows<IllegalStateException> {
                    StayState(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT)
                    )
                }
            },
            {
                // 카드가 두 장이상이지만 블랙잭 상태인 경우
                assertThrows<IllegalStateException> {
                    StayState(
                        Card.of(CardCategory.CLOVER, CardNumber.JACK),
                        Card.of(CardCategory.CLOVER, CardNumber.ACE)
                    )
                }
            },
            {
                // 카드가 두 장이상이지만 버스트 상태인 경우
                assertThrows<IllegalStateException> {
                    StayState(
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                        Card.of(CardCategory.CLOVER, CardNumber.SIX)
                    )
                }
            },
        )
    }

    @Test
    fun `플레이어가 Stay이고 딜러도 Stay이면 점수비교를 한다`() {
        val playerStayState = StayState(
            Card.of(CardCategory.CLOVER, CardNumber.FIVE),
            Card.of(CardCategory.CLOVER, CardNumber.JACK),
            Card.of(CardCategory.CLOVER, CardNumber.FOUR)
        )

        assertAll(
            "플레이어가 Stay이고 딜러도 Stay이면 점수비교를 한다",
            {
                // 플레이어 숫자가 더 큰 경우
                val dealerState = StayState(
                    Card.of(CardCategory.CLOVER, CardNumber.JACK),
                    Card.of(CardCategory.CLOVER, CardNumber.SIX)
                )
                val actual = playerStayState.resultProfit(dealerState)
                val expected = RateOfProfit.WIN_PROFIT
                Assertions.assertThat(actual).isEqualTo(expected)
            },
            {
                // 무승부인 경우
                val dealerState = StayState(
                    Card.of(CardCategory.CLOVER, CardNumber.NINE),
                    Card.of(CardCategory.CLOVER, CardNumber.JACK),
                )
                val actual = playerStayState.resultProfit(dealerState)
                val expected = RateOfProfit.DRAW_PROFIT
                Assertions.assertThat(actual).isEqualTo(expected)
            },
            {
                // 플레이어 숫자가 더 작은 경우
                val dealerState = StayState(
                    Card.of(CardCategory.CLOVER, CardNumber.FIVE),
                    Card.of(CardCategory.CLOVER, CardNumber.JACK),
                    Card.of(CardCategory.CLOVER, CardNumber.FIVE)
                )
                val actual = playerStayState.resultProfit(dealerState)
                val expected = RateOfProfit.LOSE_PROFIT
                Assertions.assertThat(actual).isEqualTo(expected)
            }
        )
    }

    @Test
    fun `딜러가 블랙잭이면 플레이어가 진다`() {
        val playerStayState = StayState(
            Card.of(CardCategory.CLOVER, CardNumber.FIVE),
            Card.of(CardCategory.CLOVER, CardNumber.JACK),
            Card.of(CardCategory.CLOVER, CardNumber.SIX)
        )

        val dealerState = BlackJackState(
            Card.of(CardCategory.CLOVER, CardNumber.JACK),
            Card.of(CardCategory.CLOVER, CardNumber.ACE)
        )
        val actual = playerStayState.resultProfit(dealerState)
        val expected = RateOfProfit.LOSE_PROFIT
        Assertions.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러가 버스트이면 플레이어가 이긴다`() {
        val playerStayState = StayState(
            Card.of(CardCategory.CLOVER, CardNumber.FIVE),
            Card.of(CardCategory.CLOVER, CardNumber.JACK),
            Card.of(CardCategory.CLOVER, CardNumber.SIX)
        )

        val dealerState = BustState(
            Card.of(CardCategory.CLOVER, CardNumber.JACK),
            Card.of(CardCategory.CLOVER, CardNumber.FIVE),
            Card.of(CardCategory.CLOVER, CardNumber.TEN)
        )
        val actual = playerStayState.resultProfit(dealerState)
        val expected = RateOfProfit.WIN_PROFIT
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
