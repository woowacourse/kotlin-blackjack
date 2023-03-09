package blackjack.model

import model.Dealer
import model.Participants
import model.Player
import model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어의 참가자 정보를 생성할 수 있다`() {
        val dealer = Dealer()
        val player1 = Player.from("jason")
        val player2 = Player.from("pobi")
        val actual = Participants(listOf(dealer) + Players(player1, player2))
        assertThat(actual.participants.size).isEqualTo(3)
        assertThat(actual.participants[0].name.value).isEqualTo("딜러")
        assertThat(actual.participants[1].name.value).isEqualTo("jason")
        assertThat(actual.participants[2].name.value).isEqualTo("pobi")
    }

    companion object {
        private fun Players(vararg player: Player): Players = Players(player.toList())
    }
}
