package domain.phase

import domain.CardDeck
import domain.Name
import domain.Participants
import domain.Player
import domain.Players

class PlayersSelectAddPhase(
    private val isPlayerCardAdd: (Name) -> Boolean,
    private val printPlayerCards: (Player) -> Unit
) : Phase {
    override fun runPhase(participants: Participants, deck: CardDeck) {
        playersSelectAddPhase(participants.players, deck)
    }

    private fun playersSelectAddPhase(players: Players, deck: CardDeck) {
        players.list.forEach { player ->
            player.playerSelectAdd(deck)
        }
    }

    private fun Player.playerSelectAdd(deck: CardDeck) {
        if (isBurst()) return
        val isAddCard = isPlayerCardAdd(name)
        if (isAddCard) addCard(deck.draw())
        printPlayerCards(this)
        if (isAddCard) playerSelectAdd(deck)
    }
}
