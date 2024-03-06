package blackjack

import blackjack.model.Players
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayersTest {
    @Test
    fun `플레이어는 1명 이상 6명 이하가 아니라면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Players.playerNamesOf(listOf("채채", "벼리", "서기", "올리브", "심지", "해나", "악어", "팡태"))
        }
    }

    @Test
    fun `플레이어는 이름이 중복되어서는 안된다`() {
        assertThrows<IllegalArgumentException> {
            Players.playerNamesOf(listOf("채채", "벼리", "채채", "벼리"))
        }
    }
}
