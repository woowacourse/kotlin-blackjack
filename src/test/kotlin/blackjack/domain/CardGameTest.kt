package blackjack.domain

import domain.CardGame
import model.CardDeck
import model.Dealer
import model.Participant
import model.Participants
import model.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardGameTest {

    @Test
    fun `카드를 참가자별로 2장씩 지급한다`() {
        val player1 = Player.from("jason")
        val player2 = Player.from("pobi")
        val cardGame = CardGame(cardDeck, Participants(Dealer(), player1, player2))
        cardGame.readyToStart()
        assertThat(player1.cards.size).isEqualTo(2)
        assertThat(player2.cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어는 bust가 아닐 때 계속해서 draw 의사가 있다면 bust 될 때까지 카드를 뽑는다`() {
        val player = Player.from("jason")
        val cardGame = CardGame(cardDeck, Participants(player))
        cardGame.drawCard(player, {}) { true }
        assertThat(player.isBust()).isTrue
    }

    @Test
    fun `딜러의 카드 합이 16 이하일 때 딜러는 16 초과가 될 때까지 카드를 뽑는다`() {
        val dealer = Dealer()
        val cardGame = CardGame(cardDeck, Participants(dealer))
        cardGame.drawCard(dealer, {}) { true }
        assertThat(dealer.isHit { true }).isFalse
    }

    companion object {
        private val cardDeck = CardDeck.createCardDeck()
        private fun Participants(vararg participant: Participant): Participants = Participants(participant.toList())
    }
}
