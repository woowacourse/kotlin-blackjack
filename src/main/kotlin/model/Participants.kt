package model

data class Participants(private val value: List<Participant>) {
    val dealer: Participant
        get() = value.find { it.isDealer() }!!

    val players: List<Participant>
        get() = value.filter { !it.isDealer() }

    fun drawFirstCard(cardDeck: CardDeck) {
        value.forEach { it.drawFirst(cardDeck) }
    }

    fun forEach(action: (Participant) -> Unit) {
        for (participant in value) action(participant)
    }

    fun getParticipantsProfitResult(): ParticipantsProfitResult {
        val dealerResult = dealer.getResult(this)
        val playerResult = players.map { it.getResult(this) }
        return ParticipantsProfitResult(listOf(dealerResult) + playerResult)
    }
}
