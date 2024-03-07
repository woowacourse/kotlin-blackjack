package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

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
