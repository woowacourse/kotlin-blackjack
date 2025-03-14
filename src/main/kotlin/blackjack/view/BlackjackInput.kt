package blackjack.view

import blackjack.model.state.CardDrawDecision
import blackjack.model.user.Player

interface BlackjackInput {
    fun readPlayerNames(): List<Player>

    fun readCardDrawChoice(player: Player): CardDrawDecision
}
