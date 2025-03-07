package blackjack.model.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StatusTest {
    @ValueSource(ints = [22, 23, 24, 100])
    @ParameterizedTest
    fun `임계값 보다 숫자가 크게 되면 Bust상태를 반환한다`(input: Int) {
        val actual = Status.isBust(input)

        assertThat(actual).isEqualTo(Status.Bust)
    }

    @Test
    fun `타켓값이 임계값보다 작으면 Lose상태를 반환한다`() {
        val actual = Status.compare(1, 21)

        assertThat(actual).isEqualTo(Status.Lose)
    }

    @Test
    fun `타켓값이 임계값보다 크면 Win상태를 반환한다`() {
        val actual = Status.compare(21, 1)

        assertThat(actual).isEqualTo(Status.Win)
    }

    @Test
    fun `타켓값이 임계값과 같으면 Draw상태를 반환한다`() {
        val actual = Status.compare(21, 21)

        assertThat(actual).isEqualTo(Status.Draw)
    }
}
