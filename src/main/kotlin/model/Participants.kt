package model

data class Participants(val dealer: Participant, val players: Players) {
    val all: List<Participant>
        get() = listOf(dealer) + players

    fun drawFirstCard(cardDeck: CardDeck) {
        all.forEach { it.drawFirst(cardDeck) }
    }

    fun forEach(action: (Participant) -> Unit) {
        for (participant in all) action(participant)
    }
}
