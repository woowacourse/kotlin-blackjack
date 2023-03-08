package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTypeTest {

    @Test
    fun `승리 일 경우에는 베팅머니의 1배를 수익으로 반환한다`() {
        // given
        val bettingMoney = 1000
        // when
        val actual = GameResultType.getProfit(bettingMoney, GameResultType.WIN)
        val expected = 1000
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `패배일 경우에는 베팅머니의 -1배를 수익으로 반환한다`() {
        // given
        val bettingMoney = 1000
        // when
        val actual = GameResultType.getProfit(bettingMoney, GameResultType.LOSE)
        val expected = -1000
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `무승부인 경우에는 베팅머니의 0배를 수익으로 반환한다`() {
        // given
        val bettingMoney = 1000
        // when
        val actual = GameResultType.getProfit(bettingMoney, GameResultType.DRAW)
        val expected = 0
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `블랙잭인 경우에는 베팅머니의 2분의3배를 수익으로 반환한다`() {
        // given
        val bettingMoney = 1000
        // when
        val actual = GameResultType.getProfit(bettingMoney, GameResultType.BLACKJACK)
        val expected = 1500
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
