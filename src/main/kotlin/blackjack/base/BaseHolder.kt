package blackjack.base

import blackjack.model.Card
import blackjack.model.GameResult
import blackjack.model.GameResult.Companion.DEFAULT_RESULT_VALUE
import blackjack.model.GameStatus

abstract class BaseHolder(
    val status: GameStatus = GameStatus(),
    gameResult: GameResult = GameResult(),
) {
    private var _gameResult: GameResult = gameResult
    val gameResult: GameResult
        get() = _gameResult

    fun takeCard(card: Card) {
        status.getCard(card)
    }

    fun changeResult(
        newWin: Int = DEFAULT_RESULT_VALUE,
        newDefeat: Int = DEFAULT_RESULT_VALUE,
        newPush: Int = DEFAULT_RESULT_VALUE,
    ) {
        _gameResult += GameResult(newWin, newDefeat, newPush)
    }
}
