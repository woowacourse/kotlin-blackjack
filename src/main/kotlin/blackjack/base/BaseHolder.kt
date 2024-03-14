package blackjack.base

import blackjack.model.GameDeck
import blackjack.model.GameResult
import blackjack.model.Hit
import blackjack.model.Running
import blackjack.model.State

abstract class BaseHolder(gameResult: GameResult = GameResult()) {
    private var _state: State = Running()
    val state: State
        get() = _state

    private var _gameResult: GameResult = gameResult
    val gameResult: GameResult
        get() = _gameResult

    fun initHands(gameDeck: GameDeck) {
        _state = Running().drawInitCards(gameDeck)
    }

    protected fun changeState(state: State) {
        _state = state
    }

    fun getCard(gameDeck: GameDeck) {
        if (state is Hit)
            changeState((state as Hit).getCard(gameDeck.drawCard()))
    }

    fun changeResult(newGameResult: GameResult) {
        _gameResult += newGameResult
    }
}
