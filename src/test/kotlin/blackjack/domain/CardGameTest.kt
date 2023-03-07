package blackjack.domain

import domain.CardGame
import model.Card
import model.CardDeck
import model.Cards
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

    companion object {
        private val cardDeck = CardDeck.createCardDeck()
        private fun Participants(vararg participant: Participant): Participants = Participants(participant.toList())
        private fun Dealer(vararg card: Card): Dealer = Dealer(Cards(card.toSet()))
    }
}
