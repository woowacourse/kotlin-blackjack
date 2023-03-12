package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    @Test
    fun `딜러와 플레이어들을 모두 가져온다`() {
        val participants = Participants(
            Players(
                Player(PlayerInfo(Name("pobi"), 10000)),
                Player(PlayerInfo(Name("jason"), 10000))
            ),
            Dealer()
        )
        val result = participants.all.joinToString(", ") { it.name.value }
        assertThat(result).isEqualTo("딜러, pobi, jason")
    }
}
