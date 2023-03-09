package model

data class Participants(val participants: List<Participant>) {
    val dealer: Participant
        get() = participants.find { it.isDealer() }!!
    val players: List<Participant>
        get() = participants.filter { !it.isDealer() }

    fun drawFirstCard(cardDeck: CardDeck) {
        participants.forEach { it.drawFirst(cardDeck) }
    }
}
