package domain

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.playerCase.RunningCase
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class RunningCaseTest {

    @Test
    fun `dealer의 카드가 블랙잭인 경우 패배한다`() {
        val actual = RunningCase.valueOf(
            playerScore = 15,
            dealerCardsState = CardsState.BlackJack
        )

        assertThat(actual).isEqualTo(RunningCase.DEALER_BLACKJACK_LOSE)
    }

    @Test
    fun `dealer의 카드가 Bust상태인 경우 승리한다`() {
        val actual = RunningCase.valueOf(
            playerScore = 15,
            dealerCardsState = CardsState.Bust
        )

        assertThat(actual).isEqualTo(RunningCase.DEALER_BUST_WIN)
    }

    @Test
    fun `dealer의 카드가 Running 상태이고 delaer점수가 플레이어의 점수보다 낮을때 승리한다`() {
        val actual = RunningCase.valueOf(
            playerScore = 15,
            dealerCardsState = CardsState.Running(10)
        )

        assertThat(actual).isEqualTo(RunningCase.WIN)
    }


    @Test
    fun `dealer의 카드가 Running 상태이고 delaer점수가 플레이어의 점수보다 높을때 패배한다`() {
        val actual = RunningCase.valueOf(
            playerScore = 15,
            dealerCardsState = CardsState.Running(18)
        )

        assertThat(actual).isEqualTo(RunningCase.LOSE)
    }


    @Test
    fun `dealer의 카드가 Running 상태이고 delaer점수가 플레이어의 점수와 같을때 무승부이다`() {
        val actual = RunningCase.valueOf(
            playerScore = 15,
            dealerCardsState = CardsState.Running(15)
        )

        assertThat(actual).isEqualTo(RunningCase.DRAW)
    }


}