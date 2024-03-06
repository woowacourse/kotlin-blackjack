package blackjack.model

import blackjack.model.GameState.Finished
import blackjack.model.GameState.Running

class Deck(
    cards: List<Card> = emptyList(),
    state: GameState = Running,
) {
    private var _cards: List<Card> = cards
    val cards: List<Card>
        get() = _cards

    private var _state: GameState = state
    val state: GameState
        get() = _state

    operator fun plus(other: Card): Deck {
        _cards += other
        updateState()
        return Deck(cards, state)
    }

    private fun updateState() {
        _state =
            when (calculate()) {
                in 0..20 -> Running
                21 -> {
                    if (cards.size == 2) {
                        Finished(UserState.BLACKJACK)
                    } else {
                        Finished(UserState.STAND)
                    }
                }

                else -> Finished(UserState.BUST)
            }
    }
    }

    fun calculate(): Int {
        var total = cards.sumOf { it.number.value }
        val aceCount = cards.count { it.number == CardNumber.ACE }

        repeat(aceCount) {
            if (total <= BLACKJACK_NUMBER) return@repeat
            total -= DIFF_ACE_TO_ONE
        }

        return total
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
        const val DIFF_ACE_TO_ONE = 10
    }
}
