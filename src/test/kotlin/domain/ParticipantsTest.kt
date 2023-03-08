package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ParticipantsTest {
    fun Player(name: String, vararg cards: Int): Player {
        return Player(
            Name(name),
            Cards(
                cards.map { number ->
                    Card.of(
                        CardCategory.CLOVER,
                        CardNumber.values().find { it.value == number.toInt() } ?: CardNumber.FIVE,
                    )
                },
            ),
        )
    }

    fun Dealer(vararg cards: Int): Dealer {
        return Dealer(
            Cards(
                cards.map { number ->
                    Card.of(
                        CardCategory.CLOVER,
                        CardNumber.values().find { it.value == number.toInt() } ?: CardNumber.FIVE,
                    )
                },
            ),
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
                    8,
                    9,
                ),
                Player(
                    "jason",
                    8,
                    9,
                ),
            ),
            dealer = Dealer(8, 9),
        )
        val result = participants.participants.joinToString(", ") { it.name.value }
        assertThat(result).isEqualTo("딜러, pobi, jason")
    }
}
