package domain.game

import domain.card.ScoreState
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class GameResultTypeTest {
    // @Test
    // fun `승리 일 경우에는 베팅머니의 1배를 수익으로 반환한다`() {
    //     // given
    //     val bettingMoney = 1000
    //     // when
    //     val actual = GameResultType.getProfit(bettingMoney, GameResultType.WIN)
    //     val expected = 1000
    //     // then
    //     assertThat(actual).isEqualTo(expected)
    // }
    //
    // @Test
    // fun `패배일 경우에는 베팅머니의 -1배를 수익으로 반환한다`() {
    //     // given
    //     val bettingMoney = 1000
    //     // when
    //     val actual = GameResultType.getProfit(bettingMoney, GameResultType.LOSE)
    //     val expected = -1000
    //     // then
    //     assertThat(actual).isEqualTo(expected)
    // }
    //
    // @Test
    // fun `무승부인 경우에는 베팅머니의 0배를 수익으로 반환한다`() {
    //     // given
    //     val bettingMoney = 1000
    //     // when
    //     val actual = GameResultType.getProfit(bettingMoney, GameResultType.DRAW)
    //     val expected = 0
    //     // then
    //     assertThat(actual).isEqualTo(expected)
    // }
    //
    // @Test
    // fun `블랙잭인 경우에는 베팅머니의 2분의3배를 수익으로 반환한다`() {
    //     // given
    //     val bettingMoney = 1000
    //     // when
    //     val actual = GameResultType.getProfit(bettingMoney, GameResultType.BLACKJACK)
    //     val expected = 1500
    //     // then
    //     assertThat(actual).isEqualTo(expected)
    // }

    @Test
    fun `플레이어의 점수가 Burst 이면 딜러의 상태와 상관없이 패배이다`() {
        // given
        val playerScoreState = ScoreState.Burst
        val dealerScoreState = ScoreState.Burst
        // when
        val actual = GameResultType.valueOf(dealerScoreState = dealerScoreState, playerScoreState = playerScoreState)
        val expected = GameResultType.LOSE
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("getStateWithoutBlackJack")
    fun `플레이어의 상태가 Blackjack 이고 딜러의 상태가 블랙잭이 아니라면 블랙잭이다`(dealerScoreState: ScoreState) {
        // given
        val playerScoreState = ScoreState.BlackJack
        // when
        val actual = GameResultType.valueOf(dealerScoreState = dealerScoreState, playerScoreState = playerScoreState)
        val expected = GameResultType.BLACKJACK
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 상태가 Blackjack 이고 딜러의 상태도 블랙잭이라면 무승부이다`() {
        // given
        val playerScoreState = ScoreState.BlackJack
        val dealerScoreState = ScoreState.BlackJack
        // when
        val actual = GameResultType.valueOf(dealerScoreState = dealerScoreState, playerScoreState = playerScoreState)
        val expected = GameResultType.DRAW
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 상태가 정상이고, 딜러의 상태만 Burst면 승리이다`() {
        // given
        val playerScoreState = ScoreState.Normal(20)
        val dealerScoreState = ScoreState.Burst
        // when
        val actual = GameResultType.valueOf(dealerScoreState = dealerScoreState, playerScoreState = playerScoreState)
        val expected = GameResultType.WIN
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `"모두 정상이고 동일한 점수일때, 승패를 확인하면, 무승부이다"`() {
        // given
        val playerScoreState = ScoreState.Normal(20)
        val dealerScoreState = ScoreState.Normal(20)
        // when
        val actual = GameResultType.valueOf(dealerScoreState = dealerScoreState, playerScoreState = playerScoreState)
        val expected = GameResultType.DRAW
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `"모두 정상이고 플레이어가 점수가 높을 때, 승패를 확인하면, 승리이다"`() {
        // given
        val playerScoreState = ScoreState.Normal(21)
        val dealerScoreState = ScoreState.Normal(20)
        // when
        val actual = GameResultType.valueOf(dealerScoreState = dealerScoreState, playerScoreState = playerScoreState)
        val expected = GameResultType.WIN
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `"모두 정상이고 딜러가 점수가 더 높을 때, 승패를 확인하면, 패배이다"`() {
        // given
        val playerScoreState = ScoreState.Normal(20)
        val dealerScoreState = ScoreState.Normal(21)
        // when
        val actual = GameResultType.valueOf(dealerScoreState = dealerScoreState, playerScoreState = playerScoreState)
        val expected = GameResultType.LOSE
        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun getStateWithoutBlackJack(): Stream<ScoreState> {
            return Stream.of(ScoreState.Burst, ScoreState.Normal(20))
        }
    }
}
