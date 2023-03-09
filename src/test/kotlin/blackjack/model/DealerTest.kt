package blackjack.model

import model.Card
import model.CardDeck
import model.Dealer
import model.GameResult
import model.Name
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러의 처음 보여지는 카드는 한 장이다`() {
        val cardDeck = CardDeck(Card(Rank.ACE, Suit.SPADE), Card(Rank.KING, Suit.CLOVER))
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        val expected = setOf(Card(Rank.ACE, Suit.SPADE))
        assertThat(dealer.getFirstOpenCards().cards).isEqualTo(expected)
    }

    @Test
    fun `카드의 합이 16을 초과하지 않으면 hit 한다`() {
        val cardDeck = CardDeck(Card(Rank.JACK, Suit.DIAMOND), Card(Rank.SIX, Suit.CLOVER))
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        assertThat(dealer.isPossibleDrawCard()).isTrue
    }

    @Test
    fun `카드의 합이 16을 초과하면 stay 한다`() {
        val cardDeck = CardDeck(Card(Rank.JACK, Suit.DIAMOND), Card(Rank.SEVEN, Suit.CLOVER))
        val dealer = Dealer()
        dealer.drawFirst(cardDeck)
        assertThat(dealer.isPossibleDrawCard()).isFalse
    }

    @Test
    fun `유저 2명 중 이긴 유저가 2명이면 딜러는 0승 2패이다`() {
        val cardDeck = CardDeck(
            Card(Rank.SEVEN, Suit.DIAMOND),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.ACE, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player("jason")
        val player2 = Player("pobi")
        dealer.drawFirst(cardDeck)
        player1.drawFirst(cardDeck)
        player2.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1, player2))
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.winCount).isEqualTo(0)
        assertThat(dealerResult.loseCount).isEqualTo(2)
    }

    @Test
    fun `유저 2명 중 이긴 유저가 1명, 진 유저가 1명이면 딜러는 1승 1패이다`() {
        val cardDeck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.ACE, Suit.CLOVER),
            Card(Rank.DEUCE, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER)
        )
        val dealer = Dealer()
        val player1 = Player("jason")
        val player2 = Player("pobi")
        dealer.drawFirst(cardDeck)
        player1.drawFirst(cardDeck)
        player2.drawFirst(cardDeck)
        val participants = Participants(dealer, Players(player1, player2))
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.winCount).isEqualTo(1)
        assertThat(dealerResult.loseCount).isEqualTo(1)
    }
    @Test
    fun `유저 2명 중 딜러 포함 모든 참가자가 bust면 딜러는 2승 0패다`() {
        val cardDeck = CardDeck(
            Card(Rank.TEN, Suit.CLOVER),
            Card(Rank.TEN, Suit.SPADE),
            Card(Rank.TEN, Suit.DIAMOND),
            Card(Rank.KING, Suit.CLOVER),
            Card(Rank.QUEEN, Suit.HEART),
            Card(Rank.JACK, Suit.CLOVER),
            Card(Rank.KING, Suit.DIAMOND),
            Card(Rank.QUEEN, Suit.SPADE),
            Card(Rank.JACK, Suit.HEART)
        )
        val dealer = Dealer()
        val player1 = Player("jason")
        val player2 = Player("pobi")
        dealer.drawFirst(cardDeck)
        dealer.cards.add(cardDeck.drawCard())
        player1.drawFirst(cardDeck)
        player1.cards.add(cardDeck.drawCard())
        player2.drawFirst(cardDeck)
        player2.cards.add(cardDeck.drawCard())
        val participants = Participants(dealer, Players(player1, player2))
        val dealerResult = GameResult(participants)
        assertThat(dealerResult.winCount).isEqualTo(2)
        assertThat(dealerResult.loseCount).isEqualTo(0)
    }

    companion object {
        private fun Player(name: String): Player = Player(Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
