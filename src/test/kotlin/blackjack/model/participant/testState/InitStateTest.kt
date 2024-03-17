package blackjack.model.participant.testState

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class InitStateTest {
    @Test
    fun `처음 시작이 블랙잭이면 블랙잭을 반환한다`() {
        val actual = InitState().nextTurn(21, false)
        assertThat(actual is Blackjack2).isTrue
    }

    @Test
    fun `블랙잭 여부에 관계없이, 유저가 Hit를 원한다면 Hit를 반환한다`() {
        val actual = InitState().nextTurn(10, true)
        assertThat(actual is Hit).isTrue
    }

    @Test
    fun `블랙잭이 아니며, Hit를 원하지 않는다면 Stay를 반환한다`() {
        val actual = InitState().nextTurn(10, false)
        assertThat(actual is Stay).isTrue
    }
}
