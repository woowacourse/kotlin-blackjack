package blackjack.model.domain

import blackjack.model.domain.participant.ParticipantStatus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantStatusTest {
    // given
    @ValueSource(ints = [22, 23, 24, 100])
    @ParameterizedTest
    fun `임계값 보다 숫자가 크게 되면 Bust상태를 반환한다`(input: Int) {
        // when
        val actual = ParticipantStatus.isBust(input)
        val expected = ParticipantStatus.Bust
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값보다 작으면 Lose상태를 반환한다`() {
        // when
        val actual = ParticipantStatus.compare(1, 21)
        val expected = ParticipantStatus.Lose
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값보다 크면 Win상태를 반환한다`() {
        // when
        val actual = ParticipantStatus.compare(21, 1)
        val expected = ParticipantStatus.Win
        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `타켓값이 임계값과 같으면 Draw상태를 반환한다`() {
        // when
        val actual = ParticipantStatus.compare(21, 21)
        val expected = ParticipantStatus.Draw
        // then
        assertThat(actual).isEqualTo(expected)
    }
}
