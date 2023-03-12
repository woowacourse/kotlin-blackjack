package domain.participant

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    fun Player(name: String, vararg cards: Int): Player {
        return Player(
            Name(name),
            BettingMoney(1000),
        )
    }

    fun Players(vararg player: Player): Players {
        return Players(player.toList())
    }

    @Test
    fun `딜러와 플레이어들을 모두 가져온다`() {
        val participants = Participants(
            players = Players(
                Player(
                    "pobi",
                ),
                Player(
                    "jason",
                ),
            ),
            dealer = Dealer(),
        )
        val result = participants.list.joinToString(", ") { it.name.value }
        assertThat(result).isEqualTo("딜러, pobi, jason")
    }
}
