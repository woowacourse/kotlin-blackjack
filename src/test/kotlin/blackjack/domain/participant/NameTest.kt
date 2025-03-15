package blackjack.domain.participant

import blackjack.model.participant.Name
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class NameTest {
    @Test
    fun `닉네임이 공백이면 오류를 반환한다`() {
        // given & when & then
        assertThrows<IllegalArgumentException> {
            Name("")
        }
    }

    @Test
    fun `닉네임이 타마면 타마라는 이름를 가진 이름 객체를 반환한다`() {
        // given & when
        val name = Name("타마")

        // then
        assertThat(name.value).isEqualTo("타마")
    }
}
