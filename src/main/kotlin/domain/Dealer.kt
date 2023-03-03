package domain

class Dealer(cards: Cards) : Participant(Name("딜러"), cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(TAKE_ONE)
    }

    override fun isPossibleDrawCard(): Boolean {
        if (cards.maxSumState().sum <= DEALER_ADD_CARD_CONDITION) return true
        return false
    }

    fun getResult(players: Players): Map<GameResult, Int> {
        val result = GameResult.values().associateWith { INITIALIZE_TO_ZERO }.toMutableMap()
        players.players.forEach { player ->
            val playerResult = player.getGameResult(getSumStateResult())
            result[playerResult] = (result[playerResult] ?: INITIALIZE_TO_ZERO) + PLUS_ONE
        }
        val winCount = result[GameResult.WIN]
        result[GameResult.WIN] = result[GameResult.LOSE] ?: INITIALIZE_TO_ZERO
        result[GameResult.LOSE] = winCount ?: INITIALIZE_TO_ZERO
        return result
    }

    companion object {
        const val DEALER_ADD_CARD_CONDITION = 16
        private const val INITIALIZE_TO_ZERO = 0
        private const val PLUS_ONE = 1
        private const val TAKE_ONE = 1
    }
}
