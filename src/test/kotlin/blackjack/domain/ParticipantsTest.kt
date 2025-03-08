package blackjack.domain

import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ParticipantsTest {
    @Test
    fun `플레이어 이름이 중복되면 에러가 발생한다`() {
        val players = listOf(Player("peto"), Player("peto"))
        assertThrows<IllegalArgumentException>(
            message = "플레이어 이름은 중복될 수 없습니다.",
        ) {
            Participants(players)
        }
    }
}
