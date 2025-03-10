package blackjack.domain.participant

import blackjack.domain.UserChoice
import blackjack.domain.deck.Deck

class Participants(
    private val participants: List<Participant>,
) {
    val dealer: Dealer = participants.filterIsInstance<Dealer>().first()
    val players: List<Player> = participants.filterIsInstance<Player>()

    fun getChoice(
        deck: Deck,
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        players.forEach { player ->
            player.choice(deck, getPlayerChoice, onPlayerStateUpdated)
        }
    }
}
