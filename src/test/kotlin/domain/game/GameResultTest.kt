package domain.game

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
import domain.card.Cards
import domain.participant.BettingMoney
import domain.participant.Dealer
import domain.participant.Name
import domain.participant.Participants
import domain.participant.Player
import domain.participant.Players
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class GameResultTest {
    fun Player(name: String, vararg cards: Int): Player {
        return Player(
            Name(name),
            Cards(
                cards.map { number ->
                    Card.of(
                        CardCategory.CLOVER,
                        CardNumber.values().find { it.value == number } ?: CardNumber.FIVE,
                    )
                },
            ),
            BettingMoney(1000),
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
    fun `플레이어의 최종 수익을 반환한다`() {
        // given
        val player1 = Player(
            "pobi",
            8,
            9,
        )
        val player2 = Player(
            "jason",
            7,
            9,
        )
        val participants = Participants(
            players = Players(
                player1,
                player2,
            ),
            dealer = Dealer(8, 9),
        )
        val gameResult = GameResult(participants)

        // when
        val actual = gameResult.getPlayersProfit()

        // then
        assertAll(
            { assertThat(actual[player1]).isEqualTo(0) },
            { assertThat(actual[player2]).isEqualTo(-1000) },
        )
    }

    @Test
    fun `딜러의 최종 수익을 반환한다`() {
        // given
        val player1 = Player(
            "pobi",
            8,
            9,
        )
        val player2 = Player(
            "jason",
            7,
            9,
        )
        val participants = Participants(
            players = Players(
                player1,
                player2,
            ),
            dealer = Dealer(8, 9),
        )
        val gameResult = GameResult(participants)

        // when
        val actual = gameResult.getDealerProfit()

        // then
        assertThat(actual.second).isEqualTo(1000)
    }
}
