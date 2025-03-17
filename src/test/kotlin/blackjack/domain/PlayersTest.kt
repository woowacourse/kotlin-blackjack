package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardRank.ACE
import blackjack.model.CardRank.KING
import blackjack.model.CardRank.NINE
import blackjack.model.CardRank.QUEEN
import blackjack.model.CardRank.TWO
import blackjack.model.CardSuit.CLUB
import blackjack.model.CardSuit.DIAMOND
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.WinningResult.BLACKJACK
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
        val playerHand =
            Hand(
                listOf(
                    Card.getCashed(TWO, CLUB),
                    Card.getCashed(NINE, CLUB),
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)

        val dealerHand =
            Hand(
                listOf(
                    Card.getCashed(TWO, DIAMOND),
                    Card.getCashed(NINE, DIAMOND),
                ),
            )
        val dealer = Dealer.makeDealer(hand = dealerHand)

        // when
        val results = player.compareHand(dealer)

        // then
        assertThat(results).isEqualTo(PUSH)
    }

    @Test
    fun `플레이어의 점수가 블랙잭이면 블랙잭을 반환한다`() {
        val playerName = "시아"
        val playerHand =
            Hand(
                listOf(
                    Card.getCashed(ACE, CLUB),
                    Card.getCashed(KING, CLUB),
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)

        val dealerHand =
            Hand(
                listOf(
                    Card.getCashed(TWO, DIAMOND),
                    Card.getCashed(NINE, DIAMOND),
                ),
            )
        val dealer = Dealer.makeDealer(hand = dealerHand)

        // when
        val results = player.compareHand(dealer)

        // then
        assertThat(results).isEqualTo(BLACKJACK)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 높으면 우승을 반환한다`() {
        val playerName = "시아"
        val playerHand =
            Hand(
                listOf(
                    Card.getCashed(QUEEN, CLUB),
                    Card.getCashed(KING, CLUB),
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)
        val dealerHand =
            Hand(
                listOf(
                    Card.getCashed(TWO, DIAMOND),
                    Card.getCashed(NINE, DIAMOND),
                ),
            )
        val dealer = Dealer.makeDealer(hand = dealerHand)

        // when
        val results = player.compareHand(dealer)

        // then
        assertThat(results).isEqualTo(WIN)
    }

    @Test
    fun `플레이어의 점수가 딜러의 점수보다 낮으면 패배를 반환한다`() {
        val playerName = "시아"
        val playerHand =
            Hand(
                listOf(
                    Card.getCashed(TWO, CLUB),
                    Card.getCashed(NINE, CLUB),
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)

        val dealerHand =
            Hand(
                listOf(
                    Card.getCashed(KING, DIAMOND),
                    Card.getCashed(QUEEN, DIAMOND),
                ),
            )
        val dealer = Dealer.makeDealer(hand = dealerHand)

        // when
        val results = player.compareHand(dealer)

        // then
        val expected = LOSE
        assertThat(results).isEqualTo(expected)
    }
}
