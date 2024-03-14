package blackjack.view

import blackjack.model.participant.Player

interface CardDecision {
    fun hasMoreCardDecision(player: Player): Boolean
}
