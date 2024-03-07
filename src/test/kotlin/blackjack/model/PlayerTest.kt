package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    class Player(name: String) : Participant(name) {
        init {
            require(name.length <= MAX_NAME_LENGTH) {
                ERROR_NAME_LENGTH
            }
        }

        companion object {

            fun checkDuplication(
                name: String,
                playerNames: Set<String>
            ) {
                require(!playerNames.contains(name)) {
                    ERROR_DUPLICATION_NAME
                }
            }

            private const val MAX_NAME_LENGTH = 8
            private const val ERROR_NAME_LENGTH = "사용자 이름은 최대 ${MAX_NAME_LENGTH}자 입니다."
            private const val ERROR_DUPLICATION_NAME = "사용자 이름은 중복이 불가능 합니다."
        }
    }

    @Test
    fun `올바르지 않은 플레이어 이름 테스트`() {
        assertThrows<IllegalArgumentException> {
            Player("이름8자이상이름인이름")
        }
    }

    @Test
    fun `올바른 플레이어 이름 테스트`() {
        val actualName = "이름8자이하"
        val player = Player(actualName)
        assertThat(player.getName()).isEqualTo(actualName)
    }

    @Test
    fun `중복 테스트에 대한 검증 테스트()`() {
        val names = setOf("꼬상", "누누")
        val nunuName = "누누"
        assertThrows<IllegalArgumentException> {
            Player.checkDuplication(nunuName, names)
        }
    }
}
