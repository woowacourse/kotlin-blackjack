package blackjack.domain

class Participants(names: List<String>, participantGenerator: ParticipantGenerator) {
    val dealer: Dealer = participantGenerator.generateDealer()
    val players: Players = Players(names, participantGenerator::generatePlayer)

    fun getConsequence(player: Player): Consequence = player.chooseWinner(dealer)

    fun getPlayersConsequences(): List<Pair<Player, Consequence>> = players.value.map { Pair(it, getConsequence(it)) }

    fun getDealersConsequence(): List<Int> {
        val dealerConsequence = mutableListOf(0, 0, 0)
        players.value.forEach { dealerConsequence[decideDealerResult(getConsequence(it))]++ }
        return dealerConsequence
    }

    private fun decideDealerResult(consequence: Consequence): Int =
        when (consequence) {
            Consequence.WIN -> DEALER_LOSE
            Consequence.LOSE -> DEALER_WIN
            Consequence.DRAW -> DRAW
        }

    companion object {
        private const val DEALER_WIN = 0
        private const val DEALER_LOSE = 1
        private const val DRAW = 2
    }
}
