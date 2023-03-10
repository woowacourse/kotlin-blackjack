package blackjack.domain

import domain.GameResult
import model.Card
import model.CardDeck
import model.Dealer
import model.Money
import model.Name
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {

    @Test
    fun `딜러가 진 경우 딜러의 수익은 0이다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.ACE, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player("jason", 1_000L)
        val player2 = Player("pobi", 1_000L)
        dealer.drawFirst(cardDeck)
        player1.drawFirst(cardDeck)
        player2.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1, player2))
        assertThat(GameResult(participants).dealerResult).isEqualTo(0L)
    }

    @Test
    fun `딜러와 플레이어가 비긴 경우 수익은 0이다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.SEVEN, Suit.CLOVER),
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.SEVEN, Suit.HEART),
            Card(Rank.TEN, Suit.HEART)
        )
        val dealer = Dealer()
        val player1 = Player("jason", 1_000L)
        val player2 = Player("pobi", 1_000L)
        dealer.drawFirst(cardDeck)
        player1.drawFirst(cardDeck)
        player2.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1, player2))
        assertThat(GameResult(participants).dealerResult).isEqualTo(0L)
    }

    @Test
    fun `딜러와 플레이어가 블랙잭인 경우 딜러와 플레이어 수익은 0이다`() {
        val cardDeck = CardDeck(
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.ACE, Suit.HEART),
            Card(Rank.TEN, Suit.DIAMOND),
        )
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        val player1 = Player("jason", 1_000L)
        player1.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1))
        assertThat(GameResult(participants).dealerResult).isEqualTo(0L)
        assertThat(GameResult(participants).playersResult[player1.name]).isEqualTo(0L)
    }

    @Test
    fun `딜러가 블랙잭이고, 1_000씩 베팅한 2명의 플레이어는 블랙잭이 아니라면 딜러의 수익은 2_000이다`() {
        val cardDeck = CardDeck(
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.DEUCE, Suit.CLOVER),
            Card(Rank.DEUCE, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player.of(Name("jason"), 1_000L)
        val player2 = Player.of(Name("pobi"), 1_000L)
        dealer.drawFirst(cardDeck)
        player1.drawFirst(cardDeck)
        player2.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1, player2))
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.dealerResult).isEqualTo(2_000L)
    }

    @Test
    fun `딜러가 블랙잭이 아니고, 1_000씩 베팅한 2명의 플레이어가 블랙잭이라면 두 플레이어의 수익은 1_5배인 1_500이다`() {
        val cardDeck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.SEVEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.ACE, Suit.DIAMOND)
        )
        val dealer = Dealer()
        val player1 = Player("jason", 1_000L)
        val player2 = Player("pobi", 1_000L)
        dealer.drawFirst(cardDeck)
        player1.drawFirst(cardDeck)
        player2.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1, player2))
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.playersResult[player1.name]).isEqualTo(1_500L)
        assertThat(dealerResult.playersResult[player2.name]).isEqualTo(1_500L)
    }

    @Test
    fun `카드 합이 딜러 17 플레이어 19라면 1_000을 베팅한 플레이어의 수익은 1배인 1_000이다`() {
        val cardDeck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.SEVEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.NINE, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player("jason", 1_000L)
        dealer.drawFirst(cardDeck)
        player1.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1))
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.playersResult[player1.name]).isEqualTo(1_000L)
    }

    @Test
    fun `카드 합이 딜러 20 플레이어 15라면 1_000을 베팅한 플레이어의 수익은 -1배인 -1_000이다`() {
        val cardDeck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.JACK, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.FIVE, Suit.CLOVER)
        )
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        val player1 = Player("jason", 1_000L)
        player1.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1))
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.playersResult[player1.name]).isEqualTo(-1_000L)
    }

    companion object {
        private fun Player(name: String, money: Long): Player = Player(Name(name), Money(money))
        private fun Players(vararg player: Player): Players = Players(player.toList())
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
