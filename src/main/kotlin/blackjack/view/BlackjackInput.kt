package blackjack.view

import blackjack.model.CardDrawDecision
import blackjack.model.Player

interface BlackjackInput {
    fun readPlayerNames(): List<Player>

    fun readCardDrawChoice(player: Player): CardDrawDecision
}
