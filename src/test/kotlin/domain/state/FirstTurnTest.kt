package domain.state

import domain.Dummy.CLOVER_ACE
import domain.Dummy.CLOVER_KING
import domain.Dummy.CLOVER_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FirstTurnTest {
    @Test
    fun `두 장의 카드 합이 21이면 BlackJack을 반환한다`() {
        val firstTurn = FirstTurn(CLOVER_ACE, CLOVER_KING)
        val actual = firstTurn.nextState()

        assertThat(actual).isInstanceOf(BlackJack::class.java)
    }

    @Test
    fun `두 장의 카드 합이 21보다 작으면 Hit을 반환한다`() {
        val firstTurn = FirstTurn(CLOVER_TWO, CLOVER_KING)
        val actual = firstTurn.nextState()

        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `stay함수를 호출하면 Stay를 반환한다`() {
        val firstTurn = FirstTurn(CLOVER_TWO, CLOVER_KING)
        val actual = firstTurn.toStay()

        assertThat(actual).isInstanceOf(Stay::class.java)
    }
}
