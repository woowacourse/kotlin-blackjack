package blackjack.domain.participant

import blackjack.domain.deck.Deck

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    fun getChoice(
        deck: Deck,
        getPlayerChoice: (String) -> Boolean,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        players.forEach { player ->
            player.choice(deck, getPlayerChoice, onPlayerStateUpdated)
        }
    }
}
