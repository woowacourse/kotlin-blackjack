package blackjack.model

import blackjack.model.participant.Dealer
import blackjack.model.participant.Participant
import blackjack.model.participant.Player

class GameManager(
    private val dealer: Dealer,
    private val players: List<Player>,
) {
    private var deck = Deck()

    fun startGame() {
        deck = Deck()
        dealInitialCardWithCount()
    }

    fun dealInitialCardWithCount() {
        repeat(INITIAL_HAND_OUT_CARD_COUNT) {
            dealer.addCard(deck.draw())
            players.forEach { player -> player.addCard(deck.draw()) }
        }
    }

    fun calculateResultMap(): Map<Player, ResultType> {
        val playersStatus =
            players.associateBy(
                { player -> player },
                { player -> ResultCalculator.judgeScore(dealer, player) },
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

    fun drawCard(person: Participant) {
        person.addCard(deck.draw())
    }

    companion object {
        const val INITIAL_HAND_OUT_CARD_COUNT = 2
    }
}
