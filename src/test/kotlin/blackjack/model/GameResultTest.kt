package blackjack.model

import model.Card
import model.Dealer
import model.FinalResult
import model.GameResult
import model.Hand
import model.Name
import model.Player
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `딜러 21점 승리 플레이어 19점, 20점 패배`() {
        val dealer = dealer(
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.JACK, Suit.DIAMOND),
            Card(Rank.ACE, Suit.DIAMOND),
        )
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.HEART), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.HEART))
        val result = GameResult.of(dealer, listOf(player1, player2))

        assertThat(result.getDealerWinResult()).isEqualTo(2)
        assertThat(result.getDealerPushResult()).isEqualTo(0)
        assertThat(result.getDealerLoseResult()).isEqualTo(0)
        assertThat(result.playersFinalResult["jason"]).isEqualTo(FinalResult.LOSE)
        assertThat(result.playersFinalResult["pobi"]).isEqualTo(FinalResult.LOSE)
    }

    @Test
    fun `딜러 22점 패배 플레이어 19점, 20점 승리`() {
        val dealer = dealer(
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.JACK, Suit.DIAMOND),
            Card(Rank.DEUCE, Suit.DIAMOND),
        )
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.HEART), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.SPADE))

        val result = GameResult.of(dealer, listOf(player1, player2))

        assertThat(result.getDealerWinResult()).isEqualTo(0)
        assertThat(result.getDealerPushResult()).isEqualTo(0)
        assertThat(result.getDealerLoseResult()).isEqualTo(2)
        assertThat(result.playersFinalResult["jason"]).isEqualTo(FinalResult.WIN)
        assertThat(result.playersFinalResult["pobi"]).isEqualTo(FinalResult.WIN)
    }

    @Test
    fun `딜러 20점 1승1패 플레이어 19점 패배, 21점 승리`() {
        val dealer = dealer(Card(Rank.JACK, Suit.HEART), Card(Rank.JACK, Suit.DIAMOND))
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.SPADE), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.DIAMOND), Card(Rank.ACE, Suit.DIAMOND))

        val result = GameResult.of(dealer, listOf(player1, player2))
        assertThat(result.getDealerWinResult()).isEqualTo(1)
        assertThat(result.getDealerPushResult()).isEqualTo(0)
        assertThat(result.getDealerLoseResult()).isEqualTo(1)
        assertThat(result.playersFinalResult["jason"]).isEqualTo(FinalResult.LOSE)
        assertThat(result.playersFinalResult["pobi"]).isEqualTo(FinalResult.WIN)
    }

    @Test
    fun `딜러 21점 1승1무, 플레이어 21점 무승부, 20점 패배`() {
        val dealer = dealer(Card(Rank.JACK, Suit.HEART), Card(Rank.ACE, Suit.DIAMOND))
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.SPADE), Card(Rank.ACE, Suit.CLOVER))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.NINE, Suit.DIAMOND), Card(Rank.ACE, Suit.DIAMOND))

        val result = GameResult.of(dealer, listOf(player1, player2))
        assertThat(result.getDealerWinResult()).isEqualTo(1)
        assertThat(result.getDealerPushResult()).isEqualTo(1)
        assertThat(result.getDealerLoseResult()).isEqualTo(0)
        assertThat(result.playersFinalResult["jason"]).isEqualTo(FinalResult.PUSH)
        assertThat(result.playersFinalResult["pobi"]).isEqualTo(FinalResult.LOSE)
    }
    @Test
    fun `딜러 20점 1승1패, 플레이어 21점 블랙잭 승리, 20점 패배`() {
        val dealer = dealer(Card(Rank.JACK, Suit.HEART), Card(Rank.JACK, Suit.DIAMOND))
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.SPADE), Card(Rank.ACE, Suit.CLOVER))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.NINE, Suit.DIAMOND), Card(Rank.ACE, Suit.DIAMOND))

        val result = GameResult.of(dealer, listOf(player1, player2))
        assertThat(result.getDealerWinResult()).isEqualTo(1)
        assertThat(result.getDealerPushResult()).isEqualTo(1)
        assertThat(result.getDealerLoseResult()).isEqualTo(0)
        assertThat(result.playersFinalResult["jason"]).isEqualTo(FinalResult.BLACKJACK_WIN)
        assertThat(result.playersFinalResult["pobi"]).isEqualTo(FinalResult.LOSE)
    }

    private fun dealer(vararg cards: Card) = Dealer(Hand(cards.toList()))
    private fun player(name: Name, vararg cards: Card) = Player(Hand(cards.toList()), name)
}
