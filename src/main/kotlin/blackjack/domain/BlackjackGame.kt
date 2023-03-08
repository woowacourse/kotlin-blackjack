package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

class BlackjackGame(
    val players: List<Player>,
    val dealer: Dealer
) {
    private val deck: Deck = Deck.create()

    fun startGame(output: (Dealer, List<Player>) -> Unit) {
        dealCards()
        output(dealer, players)
    }

    fun runPlayer(input: (String) -> (Boolean), output: (Player) -> Unit) {
        players.forEach { decideHitOrStand(it, input, output) }
    }

    fun runDealer(output: (String) -> Unit) {
        if (dealer.shouldHit()) {
            dealer.receive(deck.draw())
            output(dealer.name)
        }
    }

    private fun dealCards() {
        val participants = players + dealer
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
