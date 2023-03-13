package blackjack.domain

import domain.CardGame
import model.Card
import model.CardDeck
import model.Cards
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
        val cardGame = CardGame(cardDeck, Participants(Dealer(Cards(setOf())), Players(player1, player2)))
        cardGame.readyToStart()
        assertThat(player1.cards.size).isEqualTo(2)
        assertThat(player2.cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어는 bust가 아닐 때 계속해서 draw 의사가 있다면 bust 될 때까지 카드를 뽑는다`() {
        val needToDraw = true
        val player = Player.of(Cards(setOf()), "jason", 1_000L) { needToDraw }
        val cardGame = CardGame(cardDeck, Participants(Dealer(Cards(setOf())), Players(player)))
        cardGame.start {}
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `딜러의 카드 합이 16 이하일 때 딜러는 16 초과가 될 때까지 카드를 뽑는다`() {
        val dealer = Dealer(Cards(setOf()))
        val cardGame = CardGame(cardDeck, Participants(dealer, Players(Player("tr"))))
        cardGame.start {}
        assertThat(dealer.isHit()).isFalse
    }

    @Test
    fun `딜러가 진 경우 딜러의 수익은 플레이어 수익의 -1배인 -1_000이다`() {
        val deck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.QUEEN, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player("jason", 1_000L)
        val player2 = Player("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.start {}
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
        val player1 = Player("jason", 1_000L)
        val player2 = Player("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.start {}
        assertThat(result.dealerResult.profit.value).isEqualTo(0L)
    }

    @Test
    fun `딜러와 플레이어가 블랙잭인 경우 플레이어와 딜러의 수익은 0이다`() {
        val deck = CardDeck(
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.ACE, Suit.HEART),
            Card(Rank.TEN, Suit.DIAMOND),
        )
        val dealer = Dealer()
        val player1 = Player("jason", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1)))
        cardGame.readyToStart()
        val result = cardGame.start {}
        assertThat(result.dealerResult.profit.value).isEqualTo(0L)
        assertThat(result.playersResult[0].profit.value).isEqualTo(0L)
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
        val player1 = Player("jason", 1_000L)
        val player2 = Player("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.start {}
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
        val player1 = Player("jason", 1_000L)
        val player2 = Player("pobi", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1, player2)))
        cardGame.readyToStart()
        val result = cardGame.start {}
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
        val player1 = Player("jason", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1)))
        cardGame.readyToStart()
        val result = cardGame.start {}
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
        val player1 = Player("jason", 1_000L)
        val cardGame = CardGame(deck, Participants(dealer, Players(player1)))
        cardGame.readyToStart()
        val result = cardGame.start {}
        assertThat(result.playersResult[0].profit.value).isEqualTo(-1_000L)
    }

    companion object {
        private val cardDeck = CardDeck.createCardDeck()
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
        private fun Player(name: String): Player = Player.of(Cards(setOf()), name, 1_000L)
        private fun Player(name: String, money: Long): Player = Player.of(Cards(setOf()), name, money)
        private fun Players(vararg player: Player): Players = Players(player.toList())
        private fun Participants(dealer: Dealer, players: Players): Participants = Participants(listOf(dealer) + players)
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
