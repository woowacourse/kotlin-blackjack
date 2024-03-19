package blackjack.base

import blackjack.model.Card
import blackjack.model.GameDeck
import blackjack.model.GameResult
import blackjack.model.state.Running
import blackjack.model.state.State

abstract class BaseHolder(gameResult: GameResult = GameResult()) {
    private var _state: State = Running()
    val state: State
        get() = _state

    private var _gameResult: GameResult = gameResult
    val gameResult: GameResult
        get() = _gameResult

    fun initHands(gameDeck: GameDeck) {
        _state = Running(profit = state.profit).drawInitCards(gameDeck)
    }

    protected fun changeState(state: State) {
        _state = state
    }

    fun takeCard(card: Card) {
        changeState(state.getCard(card))
    }

    fun addResult(newGameResult: GameResult) {
        _gameResult += newGameResult
    }

    fun win(opponentProfit: Double) {
        _gameResult += GameResult().win()
        setProfitFromOpponent(opponentProfit)
    }

    fun push(opponentProfit: Double) {
        _gameResult += GameResult().push()
        setProfitFromOpponent(opponentProfit)
    }

    fun defeat(opponentProfit: Double) {
        _gameResult += GameResult().defeat()
        setProfitFromOpponent(opponentProfit)
    }

    private fun setProfitFromOpponent(opponentProfit: Double) {
        state.changeProfitByOpponent(opponentProfit)
    }
}
