package blackjack.model.result

class DealerResult(val results: Map<GameResultType, Int>) {
    constructor(results: List<GameResultType>) : this(countResult(results))

    companion object {
        private fun countResult(dealerResult: List<GameResultType>): Map<GameResultType, Int> {
            return dealerResult.groupingBy { it }.eachCount()
        }
    }
}
