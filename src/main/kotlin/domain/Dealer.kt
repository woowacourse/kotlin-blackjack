package domain

class Dealer(cards: Cards) : Participant(Name("딜러"), cards) {
    override fun showInitCards(): List<Card> {
        return cards.cards.take(1)
    }

    override fun isPossibleDrawCard(): Boolean {
        if (cards.maxSumState().sum <= 16) return true
        return false
    }

    fun getResult(players: Players): Map<GameResult, Int> {
        val result = GameResult.values().associateWith { 0 }.toMutableMap()
        players.players.forEach { player ->
            val playerResult = player.getGameResult(getSumStateResult())
            result[playerResult] = (result[playerResult] ?: 0) + 1
        }
        val winCount = result[GameResult.WIN]
        result[GameResult.WIN] = result[GameResult.LOSE] ?: 0
        result[GameResult.LOSE] = winCount ?: 0
        return result
    }
}
