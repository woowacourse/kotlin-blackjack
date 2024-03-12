package blackjack.model

import blackjack.model.GameResult.Companion.DEFAULT_RESULT_VALUE
import blackjack.state.BlackjackState
import blackjack.state.BlackjackState.Finished.Blackjack
import blackjack.state.BlackjackState.Finished.Bust
import blackjack.state.BlackjackState.Finished.Stay
import blackjack.state.BlackjackState.Running.Hit

class Hand(cards: List<Card> = emptyList()) {
    private var _cards: List<Card> = cards
    val cards: List<Card>
        get() = _cards

    private var _gameResult: GameResult = GameResult()
    val gameResult: GameResult
        get() = _gameResult

    operator fun plus(other: Card) {
        _cards += other
    }

    fun reset() {
        _cards = emptyList()
    }

    fun determineState(): BlackjackState {
        return when (calculate()) {
            in RUNNING_RANGE -> Hit
            BLACKJACK_NUMBER -> if (cards.size == MIN_CARD_COUNTS) Blackjack else Stay
            else -> Bust
        }
    }

    fun calculate(): Int {
        var total = cards.sumOf { it.number.value }
        val aceCount = cards.count { it.number == CardNumber.ACE }

        repeat(aceCount) {
            total += DIFF_ACE_TO_ONE

            if (total > BLACKJACK_NUMBER) {
                total -= DIFF_ACE_TO_ONE
                return@repeat
            }
        }
        return total
    }

    fun changeResult(
        newWin: Int = DEFAULT_RESULT_VALUE,
        newDefeat: Int = DEFAULT_RESULT_VALUE,
        newPush: Int = DEFAULT_RESULT_VALUE,
    ) {
        _gameResult =
            GameResult(
                win = gameResult.win + newWin,
                defeat = gameResult.defeat + newDefeat,
                push = gameResult.push + newPush,
            )
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
        const val DIFF_ACE_TO_ONE = 10
        const val MIN_CARD_COUNTS = 2
        val RUNNING_RANGE = (0..20)
    }
}
