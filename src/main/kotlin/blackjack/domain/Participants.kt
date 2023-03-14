package blackjack.domain

class Participants(names: List<String>, participantGenerator: ParticipantGenerator) {
    val dealer: Dealer = participantGenerator.generateDealer()
    val players: Players = Players(names, participantGenerator::generatePlayer)

    fun getPlayersPrizeMoney(): List<Pair<String, Int>> = players.value.map { it.name to it.getPrizeMoney(dealer) }

    fun getDealerResultMoney(): Int = players.value.sumOf { it.getPrizeMoney(dealer) * DEALER_RESULT_CONVERSION_RATE }

    companion object {
        private const val DEALER_RESULT_CONVERSION_RATE = -1
    }
}
