package domain.state

import domain.Dummy.CLOVER_ACE
import domain.Dummy.CLOVER_KING
import domain.Dummy.CLOVER_TEN
import domain.Dummy.CLOVER_TWO
import domain.card.HandOfCards
import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BustTest {
    @Test
    fun `toStay함수를 호출하면 StateException이 발생한다`() {
        val bust = Bust(HandOfCards(CLOVER_KING, CLOVER_TWO))
        bust.handOfCards.addCard(CLOVER_TEN)

        assertThrows<IllegalStateException> { bust.toStay() }
            .shouldHaveMessage("이미 끝났습니다. stay를 호출할 수 없습니다.")
    }

    @Test
    fun `nextState함수를 호출하면 StateException이 발생한다`() {
        val bust = Bust(HandOfCards(CLOVER_KING, CLOVER_TWO))
        bust.handOfCards.addCard(CLOVER_TEN)

        assertThrows<IllegalStateException> { bust.nextState { CLOVER_TWO } }
            .shouldHaveMessage("이미 끝났습니다. 다음 State가 없습니다.")
    }

    @Test
    fun `플레이어가 버스트일때 수익은 베팅 금액의 -1배이다`() {
        val playerState = Bust(HandOfCards(CLOVER_KING, CLOVER_TWO))
        playerState.handOfCards.addCard(CLOVER_TEN)
        val otherState = BlackJack(HandOfCards(CLOVER_KING, CLOVER_ACE))

        Assertions.assertThat(playerState.playerProfit(otherState, 1000.0)).isEqualTo(-1000.0)
    }
}
