package blackjack.model

import model.cards.Card
import model.cards.Hand
import model.cards.Rank
import model.cards.Suit
import model.participants.Bet
import model.participants.BetInfo
import model.participants.Dealer
import model.participants.Name
import model.participants.Player
import model.result.GameResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `딜러 21점 2승 플레이어1 19점 패배, 플레이어2 20점 패배`() {
        val dealer = dealer(
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.JACK, Suit.DIAMOND),
            Card(Rank.ACE, Suit.DIAMOND),
        )
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.HEART), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.HEART))
        val result = GameResult.of(dealer, BetInfo(mapOf(player1 to Bet(1000), player2 to Bet(2000))))
        assertThat(result.getDealerProfitResult()).isEqualTo(3000)
        assertThat(result.playersFinalResult[Name("jason")]).isEqualTo(-1000)
        assertThat(result.playersFinalResult[Name("pobi")]).isEqualTo(-2000)
    }

    @Test
    fun `딜러 버스트 2패 플레이어1 19점 승리, 플레이어2 20점 승리`() {
        val dealer = dealer(
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.JACK, Suit.DIAMOND),
            Card(Rank.DEUCE, Suit.DIAMOND),
        )
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.HEART), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.SPADE))

        val result = GameResult.of(dealer, BetInfo(mapOf(player1 to Bet(10000), player2 to Bet(20000))))

        assertThat(result.getDealerProfitResult()).isEqualTo(-30000)
        assertThat(result.playersFinalResult[Name("jason")]).isEqualTo(10000)
        assertThat(result.playersFinalResult[Name("pobi")]).isEqualTo(20000)
    }

    @Test
    fun `딜러 20점 1승1패 플레이어 19점 패배, 21점 승리`() {
        val dealer = dealer(Card(Rank.JACK, Suit.HEART), Card(Rank.JACK, Suit.DIAMOND))
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.SPADE), Card(Rank.NINE, Suit.DIAMOND))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.KING, Suit.DIAMOND), Card(Rank.ACE, Suit.DIAMOND))

        val result = GameResult.of(dealer, BetInfo(mapOf(player1 to Bet(10000), player2 to Bet(20000))))
        assertThat(result.getDealerProfitResult()).isEqualTo(-10000)
        assertThat(result.playersFinalResult[Name("jason")]).isEqualTo(-10000)
        assertThat(result.playersFinalResult[Name("pobi")]).isEqualTo(20000)
    }

    @Test
    fun `딜러 블랙잭 1승1무, 플레이어 블랙잭 무승부, 플레이어2 20점 패배`() {
        val dealer = dealer(Card(Rank.JACK, Suit.HEART), Card(Rank.ACE, Suit.DIAMOND))
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.SPADE), Card(Rank.ACE, Suit.CLOVER))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.NINE, Suit.DIAMOND), Card(Rank.ACE, Suit.DIAMOND))

        val result = GameResult.of(dealer, BetInfo(mapOf(player1 to Bet(20000), player2 to Bet(20000))))
        assertThat(result.getDealerProfitResult()).isEqualTo(20000)
        assertThat(result.playersFinalResult[Name("jason")]).isEqualTo(0)
        assertThat(result.playersFinalResult[Name("pobi")]).isEqualTo(-20000)
    }
    @Test
    fun `딜러 20점 1승1무, 플레이어1 블랙잭 승리, 플레이어2 20점 무승부`() {
        val dealer = dealer(Card(Rank.JACK, Suit.HEART), Card(Rank.JACK, Suit.DIAMOND))
        val player1 = player(Name("jason"), Card(Rank.JACK, Suit.SPADE), Card(Rank.ACE, Suit.CLOVER))
        val player2 = player(Name("pobi"), Card(Rank.JACK, Suit.SPADE), Card(Rank.NINE, Suit.DIAMOND), Card(Rank.ACE, Suit.DIAMOND))

        val result = GameResult.of(dealer, BetInfo(mapOf(player1 to Bet(100000), player2 to Bet(100000))))
        assertThat(result.getDealerProfitResult()).isEqualTo(-50000)
        assertThat(result.playersFinalResult[Name("jason")]).isEqualTo(50000)
        assertThat(result.playersFinalResult[Name("pobi")]).isEqualTo(0)
    }

    private fun dealer(vararg cards: Card) = Dealer(Hand(cards.toList()))
    private fun player(name: Name, vararg cards: Card) = Player(Hand(cards.toList()), name)
}
