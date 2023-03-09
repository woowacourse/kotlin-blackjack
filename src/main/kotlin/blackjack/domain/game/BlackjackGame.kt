package blackjack.domain.game

import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

class BlackjackGame {
    private val deck: Deck = Deck.create()

    fun startGame(participant: BlackjackParticipant, output: (Dealer, List<Player>) -> Unit) {
        dealCards(participant)
        output(participant.dealer, participant.players)
    }

    fun runPlayer(participant: BlackjackParticipant, input: (String) -> (Boolean), output: (Player) -> Unit) {
        participant.players.forEach { decideHitOrStand(it, input, output) }
    }

    fun runDealer(dealer: Dealer, output: (String) -> Unit) {
        if (dealer.shouldHit()) {
            dealer.receive(deck.draw())
            output(dealer.name)
        }
    }

    private fun dealCards(participant: BlackjackParticipant) {
        val participants = participant.players + participant.dealer
        repeat(Participant.INIT_CARD_SIZE) {
            participants.forEach { it.receive(deck.draw()) }
        }
    }

    private fun decideHitOrStand(player: Player, input: (String) -> (Boolean), output: (Player) -> Unit) {
        while (player.canHit() && input(player.name)) {
            player.receive(deck.draw())
            output(player)
        }
    }
}
