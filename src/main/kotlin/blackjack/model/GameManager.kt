package blackjack.model

import blackjack.model.card.Deck

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private val deck = Deck.create()

    fun dealInitialCardWithCount(count: Int) {
        distributeCardWithCount(dealer, count)
        players.forEach { player ->
            distributeCardWithCount(player, count)
        }
    }

    private fun distributeCardWithCount(
        participant: Participant,
        count: Int,
    ) {
        repeat(count) {
            participant.addCard(deck.draw())
        }
    }

    fun distributeCardWithChoice(
        choice: DrawChoice,
        player: Player,
    ): Boolean {
        if (choice.isDraw()) {
            distributeCard(player)
            return true
        }
        return false
    }

    fun distributeCard(participant: Participant) {
        participant.addCard(deck.draw())
    }

    fun calculateResultMap(): Map<Player, ResultType> {
        val playersStatus =
            players.associateBy(
                { player -> player },
                { player -> ResultType.judgeScore(dealer, player) },
            )
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
