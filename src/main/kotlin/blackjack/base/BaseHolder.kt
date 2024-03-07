package blackjack.base

import blackjack.model.Card
import blackjack.model.GameResult
import blackjack.model.GameResult.Companion.DEFAULT_RESULT_VALUE
import blackjack.model.Hand

abstract class BaseHolder(
    hand: Hand = Hand(),
    gameResult: GameResult = GameResult(),
) : BaseHuman() {
    private var _hand: Hand = hand
    val hand: Hand
        get() = _hand

    private var _gameResult: GameResult = gameResult
    val gameResult: GameResult
        get() = _gameResult

    fun takeCard(card: Card) {
        _hand += card
    }

    fun changeResult(
        newWin: Int = DEFAULT_RESULT_VALUE,
        newDefeat: Int = DEFAULT_RESULT_VALUE,
        newPush: Int = DEFAULT_RESULT_VALUE,
    ) {
        _gameResult = gameResult.deepCopy(newWin = newWin, newDefeat = newDefeat, newPush = newPush)
    }
}
