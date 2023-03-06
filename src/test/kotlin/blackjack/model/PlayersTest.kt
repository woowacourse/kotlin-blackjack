package blackjack.model

import model.Card
import model.Cards
import model.Name
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `players를 생성할 수 있다`() {
        val players = Players(
            listOf(
                Player(
                    Cards(
                        setOf(
                            Card(Rank.ACE, Suit.HEART)
                        )
                    ),
                    Name("jason")
                ),
                Player(
                    Cards(
                        setOf(
                            Card(Rank.DEUCE, Suit.HEART)
                        )
                    ),
                    Name("pobi")
                )
            )
        )
        assertThat(players.size).isEqualTo(2)
        assertThat(players.players[0].name.value).isEqualTo("jason")
        assertThat(players.players[1].name.value).isEqualTo("pobi")
    }
}
