package domain

import blackjack.domain.card.CardsState
import org.assertj.core.api.Java6Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardsStateTest {

    @Test
    fun `처음 카드 두장을 받았을때의 합이 21이라면 Blackjack 상태이다`() {
        assertThat(
            CardsState.valueOf(
                score = 21,
                isInitialDraw = true
            )
        ).isEqualTo(CardsState.BlackJack)
    }

    @Test
    fun `처음 받은 카드 두장이 아닐때의 합이 21이라면 Running 상태이다`() {
        assertThat(
            CardsState.valueOf(21)
        ).isEqualTo(CardsState.Running(21))
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20])
    fun `가지고 있는 카드의 최소 합이 21이하인 경우 RUNNING 상태이다`(score: Int) {
        assertThat(
            CardsState.valueOf(score)
        ).isEqualTo(CardsState.Running(score))
    }

    @Test
    fun `카드의 최소 합이 21보다 커지면 Bust 상태가 된다`() {
        assertThat(
            CardsState.valueOf(22)
        ).isEqualTo(CardsState.Bust)
    }
}
