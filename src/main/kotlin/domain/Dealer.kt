package domain

class Dealer(cards: Cards) : Participant(Name("딜러"), cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(TAKE_ONE)
    }

    override fun isPossibleDrawCard(): Boolean = resultSum() <= DEALER_ADD_CARD_CONDITION

    fun getResult(players: Players): Map<GameResult, Int> {
        val playersResult = players.result(this)
        return mapOf(
            GameResult.WIN to (playersResult[GameResult.LOSE] ?: INITIALIZE_TO_ZERO),
            GameResult.DRAW to (playersResult[GameResult.DRAW] ?: INITIALIZE_TO_ZERO),
            GameResult.LOSE to (playersResult[GameResult.WIN] ?: INITIALIZE_TO_ZERO)
        )
    }

    companion object {
        const val DEALER_ADD_CARD_CONDITION = 16
        private const val TAKE_ONE = 1
        private const val INITIALIZE_TO_ZERO = 0
    }
}
