package model

data class Participants(val participants: List<Participant>) {
    fun forEach(action: (Participant) -> Unit) {
        for (participant in participants) action(participant)
    }
}
