package blackjack.view

import blackjack.model.BetMoney
import blackjack.model.state.CardDrawDecision
import blackjack.model.user.Player

interface BlackjackInput {
    fun readPlayerNames(): List<String>

    fun readPlayerBetAmount(name: String): BetMoney

    fun readCardDrawChoice(player: Player): CardDrawDecision
}
