package domain.result

import domain.participants.Dealer

data class ParticipantsResult(val dealer: Dealer, val playerResult: List<PlayerResult>) {
    fun getDealerProfit(): Int {
        return playerResult.sumOf { it.profit * REVERSE }
    }

    companion object {
        private const val REVERSE = -1
    }
}
