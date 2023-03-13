package blackjack.domain

import domain.CardGame
import model.Card
import model.CardDeck
import model.Dealer
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardGameTest {

    @Test
    fun `카드를 참가자별로 2장씩 지급한다`() {
        val player1 = Player("jason")
        val player2 = Player("pobi")
        val cardGame = CardGame(cardDeck, Participants(Dealer(), Players(player1, player2)))
        cardGame.readyToStart()
        assertThat(player1.cards.size).isEqualTo(2)
        assertThat(player2.cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어는 bust가 아닐 때 계속해서 draw 의사가 있다면 bust 될 때까지 카드를 뽑는다`() {
        val player = Player("jason")
        val cardGame = CardGame(cardDeck, Participants(Dealer(), Players(player)))
        cardGame.drawCard(player, {}) { true }
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `딜러의 카드 합이 16 이하일 때 딜러는 16 초과가 될 때까지 카드를 뽑는다`() {
        val dealer = Dealer()
        val cardGame = CardGame(cardDeck, Participants(dealer, Players(Player("tr"))))
        cardGame.drawCard(dealer, {}) { true }
        assertThat(dealer.isHit { true }).isFalse
    }

    @Test
    fun `딜러가 진 경우 딜러의 수익은 플레이어 수익의 -1배이다`() {
        val deck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.QUEEN, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player.of("jason", 1_000L)
        val player2 = Player.of("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.getGameResult()
        assertThat(result.dealerResult.profit.value).isEqualTo(-2_000L)
    }

    @Test
    fun `딜러와 플레이어가 비긴 경우 딜러의 수익은 0이다`() {
        val deck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.SEVEN, Suit.CLOVER),
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.SEVEN, Suit.HEART),
            Card(Rank.TEN, Suit.HEART)
        )
        val dealer = Dealer()
        val player1 = Player.of("jason", 1_000L)
        val player2 = Player.of("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.getGameResult()
        assertThat(result.dealerResult.profit.value).isEqualTo(0L)
    }

    @Test
    fun `딜러와 플레이어가 블랙잭인 경우 플레이어는 베팅 금액을 돌려 받는다`() {
        val deck = CardDeck(
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.ACE, Suit.HEART),
            Card(Rank.TEN, Suit.DIAMOND),
        )
        val dealer = Dealer()
        val player1 = Player.of("jason", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1)))
        cardGame.readyToStart()
        val result = cardGame.getGameResult()
        assertThat(result.dealerResult.profit.value).isEqualTo(-1_000L)
        assertThat(result.playersResult[0].profit.value).isEqualTo(1_000L)
    }

    @Test
    fun `딜러가 블랙잭이고, 1_000씩 베팅한 2명의 플레이어는 블랙잭이 아니라면 딜러의 수익은 2_000이다`() {
        val deck = CardDeck(
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.DEUCE, Suit.CLOVER),
            Card(Rank.DEUCE, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player.of("jason", 1_000L)
        val player2 = Player.of("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.getGameResult()
        assertThat(result.dealerResult.profit.value).isEqualTo(2_000L)
    }

    @Test
    fun `딜러가 블랙잭이 아니고, 1_000씩 베팅한 2명의 플레이어가 블랙잭이라면 두 플레이어의 수익은 1_5배인 1_500이다`() {
        val deck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.SEVEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.JACK, Suit.HEART),
            Card(Rank.ACE, Suit.DIAMOND)
        )
        val dealer = Dealer()
        val player1 = Player.of("jason", 1_000L)
        val player2 = Player.of("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.getGameResult()
        assertThat(result.playersResult[0].profit.value).isEqualTo(1_500L)
        assertThat(result.playersResult[1].profit.value).isEqualTo(1_500L)
    }

    @Test
    fun `카드 합이 딜러 17 플레이어 19라면 1_000을 베팅한 플레이어의 수익은 1배인 1_000이다`() {
        val deck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.SEVEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.NINE, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player.of("jason", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1)))
        cardGame.readyToStart()
        val result = cardGame.getGameResult()
        assertThat(result.playersResult[0].profit.value).isEqualTo(1_000L)
    }

    @Test
    fun `카드 합이 딜러 20 플레이어 15라면 1_000을 베팅한 플레이어의 수익은 -1배인 -1_000이다`() {
        val deck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.JACK, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.FIVE, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player.of("jason", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1)))
        cardGame.readyToStart()
        val result = cardGame.getGameResult()
        assertThat(result.playersResult[0].profit.value).isEqualTo(-1_000L)
    }

    companion object {
        private val cardDeck = CardDeck.createCardDeck()
        private fun Player(name: String): Player = Player.of(name, 1_000L)
        private fun Players(vararg player: Player): Players = Players(player.toList())
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
