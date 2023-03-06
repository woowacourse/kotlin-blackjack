package domain

class Dealer(cards: Cards) : Participant(Name("딜러"), cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(TAKE_ONE)
    }

    override fun isPossibleDrawCard(): Boolean = cards.maxSumState().sum <= DEALER_ADD_CARD_CONDITION

    fun getResult(players: Players): Map<GameResultType, Int> {
        val result = GameResultType.values().associateWith { INITIALIZE_TO_ZERO }.toMutableMap()
        players.list.forEach { player ->
            val playerResult = player.getGameResult(getSumStateResult())
            result[playerResult] = (result[playerResult] ?: INITIALIZE_TO_ZERO) + PLUS_ONE
        }
        val winCount = result[GameResultType.WIN]
        result[GameResultType.WIN] = result[GameResultType.LOSE] ?: INITIALIZE_TO_ZERO
        result[GameResultType.LOSE] = winCount ?: INITIALIZE_TO_ZERO
        return result
    }

    companion object {
        const val DEALER_ADD_CARD_CONDITION = 16
        private const val INITIALIZE_TO_ZERO = 0
        private const val PLUS_ONE = 1
        private const val TAKE_ONE = 1
    }
}
