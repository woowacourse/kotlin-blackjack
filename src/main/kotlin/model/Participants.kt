package model

import model.Dealer.Companion.DEALER

data class Participants(val participants: List<Participant>) {
    fun getPlayerNames(): Names = Names(participants.filter { it.name.value != DEALER }.map { it.name })
}
