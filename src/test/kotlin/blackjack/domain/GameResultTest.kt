package blackjack.domain

import domain.GameResult
import model.Card
import model.CardDeck
import model.Dealer
import model.Name
import model.Participants
import model.Player
import model.Players
import model.Rank
import model.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class GameResultTest {

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
        Assertions.assertThat(dealerResult.winCount).isEqualTo(0)
        Assertions.assertThat(dealerResult.loseCount).isEqualTo(2)
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
        Assertions.assertThat(dealerResult.winCount).isEqualTo(1)
        Assertions.assertThat(dealerResult.loseCount).isEqualTo(1)
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
        Assertions.assertThat(dealerResult.winCount).isEqualTo(2)
        Assertions.assertThat(dealerResult.loseCount).isEqualTo(0)
    }
    companion object {
        private fun Player(name: String): Player = Player(Name(name))
        private fun Players(vararg player: Player): Players = Players(player.toList())
        private fun CardDeck(vararg card: Card): CardDeck = CardDeck(card.toList())
    }
}
