package model

import model.Dealer.Companion.DEALER

data class Participants(val participants: List<Participant>) {
    val dealer: Participant
        get() = participants.find { it.name.value == DEALER }!!
    val players: List<Participant>
        get() = participants.filter { it.name.value != DEALER }

    fun forEach(action: (Participant) -> Unit) {
        for (participant in participants) action(participant)
    }
}
