package blackjack.domain

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
                    TWO_CLUB,
                    NINE_CLUB,
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)

        val dealerHand =
            Hand(
                listOf(
                    TWO_CLUB,
                    NINE_CLUB,
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
                    ACE_CLUB,
                    KING_CLUB,
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)

        val dealerHand =
            Hand(
                listOf(
                    TWO_CLUB,
                    NINE_CLUB,
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
                    QUEEN_CLUB,
                    KING_CLUB,
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)
        val dealerHand =
            Hand(
                listOf(
                    TWO_CLUB,
                    NINE_CLUB,
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
                    TWO_CLUB,
                    NINE_CLUB,
                ),
            )
        val player = Player.makePlayer(playerName, playerHand)

        val dealerHand =
            Hand(
                listOf(
                    KING_CLUB,
                    QUEEN_CLUB,
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
