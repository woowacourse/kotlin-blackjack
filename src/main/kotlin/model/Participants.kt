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
        return ParticipantsProfitResult(
            listOf((dealer as Dealer).getGameProfitMoney(Participants(players))) +
                Players(players.map { it as Player }).getGameProfitMoney(dealer)
        )
    }
}
