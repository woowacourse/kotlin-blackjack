package blackjack.view

import blackjack.model.participant.Player

interface CardDecision {
    fun isMoreCardDecision(player: Player): Boolean
}
