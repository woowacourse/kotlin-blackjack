package domain.phase

import domain.Name
import domain.Participants
import domain.Player
import domain.Players
import domain.card.CardDeck

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
        if (isBust()) return
        val isAddCard = isPlayerCardAdd(name)
        if (isAddCard) addCard(deck.draw())
        printPlayerCards(this)
        if (isAddCard) playerSelectAdd(deck)
        else stay()
    }
}
