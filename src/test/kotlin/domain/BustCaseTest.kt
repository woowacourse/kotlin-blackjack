package domain

import blackjack.domain.card.CardsState
import blackjack.domain.gameResult.playerCase.BustCase
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test

class BustCaseTest {

    @Test
    fun `딜러의 카드가 Bust 상태인 경우 승리한다`() {
        val actual = BustCase.valueOf(
            dealerCardsState = CardsState.Bust
        )

        assertThat(actual).isEqualTo(BustCase.WIN)
    }

    @Test
    fun `딜러의 카드가 Running 상태인 경우 패배한다`() {
        val actual = BustCase.valueOf(
            dealerCardsState = CardsState.Running(10)
        )

        assertThat(actual).isEqualTo(BustCase.LOSE)
    }
}
