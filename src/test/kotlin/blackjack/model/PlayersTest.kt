package blackjack.model

import model.Cards
import model.Name
import model.Player
import model.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `players를 생성할 수 있다`() {
        val players = Players(
            listOf(
                Player(emptyCards(), Name("jason")),
                Player(emptyCards(), Name("pobi"))
            )
        )
        assertThat(players.toList().size).isEqualTo(2)
        assertThat(players.toList()[0].name.value).isEqualTo("jason")
        assertThat(players.toList()[1].name.value).isEqualTo("pobi")
    }

    private fun emptyCards() = Cards(emptyList())
}
