package blackjack.model

import model.cards.Hand
import model.participants.Name
import model.participants.Player
import model.participants.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `players를 생성할 수 있다`() {
        val players = Players(
            listOf(
                Player(emptyHand(), Name("jason")),
                Player(emptyHand(), Name("pobi"))
            )
        )

        val actual = players.toList()
        assertThat(actual.size).isEqualTo(2)
        assertThat(actual[0].name.value).isEqualTo("jason")
        assertThat(actual[1].name.value).isEqualTo("pobi")
    }

    private fun emptyHand() = Hand(emptyList())
}
