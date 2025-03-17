package blackjack.view

import blackjack.model.Money
import blackjack.model.state.CardDrawDecision
import blackjack.model.user.Player

interface BlackjackInput {
    fun readPlayerNames(): List<String>

    fun readPlayerBetAmount(name: String): Money

    fun readCardDrawChoice(player: Player): CardDrawDecision
}
