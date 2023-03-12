package domain.game

import domain.card.Card
import domain.card.CardCategory
import domain.card.CardNumber
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
    private fun Card(number: Int): Card {
        return Card.of(CardCategory.CLOVER, CardNumber.values().find { it.value == number } ?: CardNumber.FIVE)
    }

    fun Player(name: String): Player {
        return Player(
            Name(name),
            BettingMoney(1000),
        )
    }

    fun Players(vararg player: Player): Players {
        return Players(player.toList())
    }

    @Test
    fun `플레이어의 최종 수익을 반환한다`() {
        // given
        val player1 = Player("pobi")
        val player2 = Player("jason")
        val dealer = Dealer()

        // when
        player1.draw(Card(2))
        player1.draw(Card(3))
        player1.stopDraw()
        player2.draw(Card(6))
        player2.draw(Card(7))
        player2.stopDraw()
        dealer.draw(Card(10))
        dealer.draw(Card(2))
        dealer.stopDraw()
        val participants = Participants(
            players = Players(
                player1,
                player2,
            ),
            dealer = dealer,
        )
        val gameResult = GameResult(participants)
        val actual = gameResult.getPlayersProfit()

        // then
        assertAll(
            { assertThat(actual[player1]).isEqualTo(-1000.0) },
            { assertThat(actual[player2]).isEqualTo(1000.0) },
        )
    }

    @Test
    fun `딜러의 최종 수익을 반환한다`() {
        // given
        val player1 = Player("pobi")
        val player2 = Player("jason")
        val dealer = Dealer()

        // when
        player1.draw(Card(2))
        player1.draw(Card(3))
        player1.stopDraw()
        player2.draw(Card(6))
        player2.draw(Card(7))
        player2.stopDraw()
        dealer.draw(Card(10))
        dealer.draw(Card(2))
        dealer.stopDraw()
        val participants = Participants(
            players = Players(
                player1,
                player2,
            ),
            dealer = dealer,
        )
        val gameResult = GameResult(participants)
        val actual = gameResult.getDealerProfit().second

        // then
        assertThat(actual).isEqualTo(0.0)
    }
}
