package blackjack.model

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    fun dealInitialCardWithCount(count: Int) {
        dealer.addCards(Deck.drawWithCount(count))
        players.forEach { player -> player.addCards(Deck.drawWithCount(count)) }
    }

    fun calculateResultMap(): Map<Player, ResultType> {
        val dealerScore = dealer.calculateTotalScore()
        val playersStatus = players.associateBy({ it }, { ResultType.judgeScore(dealerScore, it.adjustScore()) })
        return playersStatus
    }

    fun calculateDealerResult(resultMap: Map<Player, ResultType>): Map<ResultType, Int> {
        val result = mutableMapOf<ResultType, Int>()

        resultMap.forEach {
            when (it.value) {
                ResultType.WIN -> result[ResultType.LOSS] = result.getOrDefault(ResultType.LOSS, 0) + 1
                ResultType.TIE -> result[ResultType.TIE] = result.getOrDefault(ResultType.TIE, 0) + 1
                ResultType.LOSS -> result[ResultType.WIN] = result.getOrDefault(ResultType.WIN, 0) + 1
            }
        }

        return result
    }
}
