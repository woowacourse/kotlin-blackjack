package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

class BlackjackGame {
    private val deck: Deck = Deck.create()

    fun startGame(participant: BlackjackParticipant, outcome: (Dealer, List<Player>) -> Unit) {
        dealCards(participant)
        outcome(participant.dealer, participant.players)
    }

    fun runPlayer(players: List<Player>, isHit: (String) -> (Boolean), outcome: (Player) -> Unit) {
        players.forEach { decideHitOrStand(it, isHit, outcome) }
    }

    fun runDealer(dealer: Dealer, outcome: (String) -> Unit) {
        if (dealer.shouldHit()) {
            dealer.receive(deck.draw())
            outcome(dealer.name)
        }
    }

    private fun dealCards(participant: BlackjackParticipant) {
        val participants = participant.players + participant.dealer
        repeat(Participant.INIT_CARD_SIZE) {
            participants.forEach { it.receive(deck.draw()) }
        }
    }

    private fun decideHitOrStand(player: Player, isHit: (String) -> (Boolean), outcome: (Player) -> Unit) {
        while (player.canHit() && isHit(player.name)) {
            player.receive(deck.draw())
            outcome(player)
        }
    }
}
