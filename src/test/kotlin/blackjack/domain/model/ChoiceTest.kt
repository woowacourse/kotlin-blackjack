package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class ChoiceTest {
    @Test
    fun `y가 입력될 시 true를 반환한다`() {
        assertThat(Choice("y").isHit()).isEqualTo(true)
    }

    @Test
    fun `n이 입력될 시 true를 반환한다`() {
        assertThat(Choice("n").isHit()).isEqualTo(false)
    }

    @Test
    fun `y 또는 n이 아닌 것이 입력될 시 오류가 발생한다`() {
        assertThatThrownBy { Choice("f") }.isInstanceOf(IllegalArgumentException::class.java).hasMessage("y 또는 n을 입력해주세요.")
    }
}
