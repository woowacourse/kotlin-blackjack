package domain

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.playerCase.BlackJackCase
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackCaseTest {

    @Test
    fun `dealer 카드의 총합이 10점으로 Running 상태인 경우 승리한다`() {
        val actual = BlackJackCase.valueOf(
            dealerCardsState = CardsState.Running(10)
        )

        assertThat(actual).isEqualTo(BlackJackCase.WIN)
    }

    @Test
    fun `dealer의 카드가 Bust 상태인 경우 승리한다`() {
        val actual = BlackJackCase.valueOf(
            dealerCardsState = CardsState.Bust
        )

        assertThat(actual).isEqualTo(BlackJackCase.WIN)
    }

    @Test
    fun `dealer가 블랙잭인 경우 무승부이다`() {
        val actual = BlackJackCase.valueOf(
            dealerCardsState = CardsState.BlackJack
        )

        assertThat(actual).isEqualTo(BlackJackCase.DRAW)
    }
}
