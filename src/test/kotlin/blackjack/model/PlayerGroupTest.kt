package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerGroupTest {
    @Test
    fun `게임 참가자를 추가할 수 있다`() {
        val participants = PlayerGroup()
        val playerNames = listOf("호두", "에디", "레오", "예니")
        participants.addPlayer(playerNames)
        assertThat(participants.players.size).isEqualTo(playerNames.size)
    }
}
