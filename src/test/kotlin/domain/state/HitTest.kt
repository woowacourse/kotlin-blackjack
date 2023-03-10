package domain.state

import domain.Dummy.CLOVER_KING
import domain.Dummy.CLOVER_THREE
import domain.Dummy.CLOVER_TWO
import domain.Dummy.HEART_KING
import domain.card.HandOfCards
import io.kotest.matchers.throwable.shouldHaveMessage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class HitTest {
    @Test
    fun `카드를 받고 카드의 합이 21초과이면 Bust를 반환한다`() {
        val hit = Hit(HandOfCards(CLOVER_KING, HEART_KING))
        val actual = hit.nextState { CLOVER_TWO }

        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `카드를 받고 카드의 합이 21이하이면 Hit를 반환한다`() {
        val hit = Hit(HandOfCards(CLOVER_KING, CLOVER_THREE))
        val actual = hit.nextState { CLOVER_TWO }

        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `stay를 호출하면 Stay를 반환한다`() {
        val hit = Hit(HandOfCards(CLOVER_KING, CLOVER_THREE))
        val actual = hit.toStay()

        assertThat(actual).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `profit함수를 호출하면 StateException이 발생한다`() {
        val hit = Hit(HandOfCards(CLOVER_KING, CLOVER_THREE))

        assertThrows<IllegalStateException> { hit.profit(0.0) }.shouldHaveMessage("아직 진행중입니다")
    }

    @Test
    fun `isFinished는 항상 false이다`() {
        val hit = Hit(HandOfCards(CLOVER_KING, CLOVER_THREE))

        val actual = hit.isFinished

        assertThat(actual).isEqualTo(false)
    }
}
