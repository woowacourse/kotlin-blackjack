package blackjack.domain.participant

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class ParticipantNameTest {
    @Test
    fun `참가자 이름을 생성할 때 한글 또는 영어가 아닌 문자가 있으면 에러가 발생한다`() {
        val name = "asdf1"
        assertThatIllegalArgumentException().isThrownBy { ParticipantName(name) }
            .withMessage("게임 참여자의 이름은 2자 이상 20자 이하의 한글 또는 영문으로 이루어져야 합니다.\n잘못된 값: $name")
    }

    @ParameterizedTest
    @ValueSource(strings = ["a", "abcdefghijklmnopqrstu"])
    fun `참가자 이름을 생성할 때 이름이 2자 이상 20자 이하가 아니면 에러가 발생한다`(name: String) {
        assertThatIllegalArgumentException().isThrownBy { ParticipantName(name) }
            .withMessage("게임 참여자의 이름은 2자 이상 20자 이하의 한글 또는 영문으로 이루어져야 합니다.\n잘못된 값: $name")
    }

    @Test
    fun `참가자 이름이 2자 이상 20자 이하의 한글 또는 영어로 이루어져 있다면 참가자 이름을 생성할 수 있다`() {
        val name = "tho마스"

        assertDoesNotThrow { ParticipantName(name) }
    }
}
