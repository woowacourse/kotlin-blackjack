package blackjack.domain.winning

import blackjack.model.hand.HandState
import blackjack.model.winning.WinningState
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class WinningStateTest {
    @Test
    fun `플레이어가 블랙잭이고 딜러가 블랙잭이 아니면 WIN_BY_BLACKJACK`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 21,
                dealerScore = 20,
                playerHandState = HandState.BLACKJACK,
                dealerHandState = HandState.ALIVE,
            )

        // then
        assertEquals(WinningState.WIN_BY_BLACKJACK, result)
    }

    @Test
    fun `플레이어와 딜러가 모두 블랙잭이면 PUSH`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 21,
                dealerScore = 21,
                playerHandState = HandState.BLACKJACK,
                dealerHandState = HandState.BLACKJACK,
            )

        // then
        assertEquals(WinningState.PUSH, result)
    }

    @Test
    fun `딜러가 블랙잭이면 플레이어는 LOSE`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 21,
                dealerScore = 21,
                playerHandState = HandState.ALIVE,
                dealerHandState = HandState.BLACKJACK,
            )

        // then
        assertEquals(WinningState.LOSE, result)
    }

    @Test
    fun `플레이어가 버스트이면 LOSE`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 22,
                dealerScore = 18,
                playerHandState = HandState.BUST,
                dealerHandState = HandState.ALIVE,
            )

        // then
        assertEquals(WinningState.LOSE, result)
    }

    @Test
    fun `딜러가 버스트이면 플레이어는 WIN_DEFAULT`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 18,
                dealerScore = 22,
                playerHandState = HandState.ALIVE,
                dealerHandState = HandState.BUST,
            )

        // then
        assertEquals(WinningState.WIN_DEFAULT, result)
    }

    @Test
    fun `플레이어 점수가 더 높으면 WIN_DEFAULT`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 19,
                dealerScore = 18,
                playerHandState = HandState.ALIVE,
                dealerHandState = HandState.ALIVE,
            )

        // then
        assertEquals(WinningState.WIN_DEFAULT, result)
    }

    @Test
    fun `딜러 점수가 더 높으면 LOSE`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 18,
                dealerScore = 19,
                playerHandState = HandState.ALIVE,
                dealerHandState = HandState.ALIVE,
            )

        // then
        assertEquals(WinningState.LOSE, result)
    }

    @Test
    fun `점수가 같으면 PUSH`() {
        // given && when
        val result =
            WinningState.fromPlayer(
                playerScore = 20,
                dealerScore = 20,
                playerHandState = HandState.ALIVE,
                dealerHandState = HandState.ALIVE,
            )

        // then
        assertEquals(WinningState.PUSH, result)
    }

    @Test
    fun `reverseToDealer가 정상 동작한다`() {
        // given && when && then
        assertEquals(WinningState.LOSE, WinningState.WIN_BY_BLACKJACK.reverseToDealer())
        assertEquals(WinningState.LOSE, WinningState.WIN_DEFAULT.reverseToDealer())
        assertEquals(WinningState.WIN_DEFAULT, WinningState.LOSE.reverseToDealer())
        assertEquals(WinningState.PUSH, WinningState.PUSH.reverseToDealer())
    }
}
