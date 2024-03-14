package model.participants

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class ParticipantNameTest {
    @ParameterizedTest
    @ValueSource(strings = ["OverflowNaming", "", "YoonSongHyun", "HwangTaeJune"])
    fun `이름의 길이가 10을 초과시 예외 발생`(name: String) {
        assertThatThrownBy {
            ParticipantName.fromInput(name)
        }.isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessage(ParticipantName.ERROR_INVALID_LENGTH)
    }
}
