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
        if (isPossibleDrawCard().not()) return
        when (isPlayerCardAdd(name)) {
            true -> playerSelectCardAddStep(deck)
            false -> playerSelectStayStep()
        }
    }

    private fun Player.playerSelectCardAddStep(deck: CardDeck) {
        addCard(deck.draw())
        printPlayerCards(this)
        playerSelectAdd(deck)
    }

    private fun Player.playerSelectStayStep() {
        printPlayerCards(this)
        stay()
    }
}
