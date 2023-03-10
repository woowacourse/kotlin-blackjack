package blackjack.domain

import domain.CardGame
import model.CardDeck
import model.Dealer
import model.Money
import model.Name
import model.Participants
import model.Player
import model.Players
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

    companion object {
        private val cardDeck = CardDeck.createCardDeck()
        private fun Player(name: String): Player = Player(Name(name), Money(1_000L))
        private fun Players(vararg player: Player): Players = Players(player.toList())
    }
}
