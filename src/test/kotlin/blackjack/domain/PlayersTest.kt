package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardRank.ACE
import blackjack.model.CardRank.KING
import blackjack.model.CardRank.NINE
import blackjack.model.CardRank.QUEEN
import blackjack.model.CardRank.TWO
import blackjack.model.CardSuit.CLUB
import blackjack.model.CardSuit.DIAMOND
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Dealer
import blackjack.model.WinningResult
import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH
import blackjack.model.WinningResult.WIN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `딜러와 플레이어의 점수가 같으면 무승부를 반환한다`() {
        // given
        val playerName = "시아"
        val playerCards = listOf(
            Card.getCard(TWO, CLUB),
            Card.getCard(NINE, CLUB)
        )
        val player = Player(playerName, playerCards)
        val players = Players(listOf(player))

        val dealerCards = listOf(
            Card.getCard(TWO, DIAMOND),
            Card.getCard(NINE, DIAMOND)
        )
        val dealer = Dealer(dealerCards)

        // when
        val results = players.results(dealer)

        // then
        val expected = mapOf(playerName to PUSH)
        assertThat(results).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 높으면 우승을 반환한다`() {
        val playerName = "시아"
        val playerCards = listOf(
            Card.getCard(ACE, CLUB),
            Card.getCard(KING, CLUB)
        )
        val player = Player(playerName, playerCards)
        val players = Players(listOf(player))

        val dealerCards = listOf(
            Card.getCard(TWO, DIAMOND),
            Card.getCard(NINE, DIAMOND)
        )
        val dealer = Dealer(dealerCards)

        // when
        val results = players.results(dealer)

        // then
        val expected = mapOf(playerName to WIN)
        assertThat(results).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 낮으면 패배를 반환한다`() {

        val playerName = "시아"
        val playerCards = listOf(
            Card.getCard(TWO, CLUB),
            Card.getCard(NINE, CLUB)
        )
        val player = Player(playerName, playerCards)
        val players = Players(listOf(player))

        val dealerCards = listOf(
            Card.getCard(KING, DIAMOND),
            Card.getCard(QUEEN, DIAMOND)
        )
        val dealer = Dealer(dealerCards)

        // when
        val results = players.results(dealer)

        // then
        val expected = mapOf(playerName to LOSE)
        assertThat(results).isEqualTo(expected)
    }
}
