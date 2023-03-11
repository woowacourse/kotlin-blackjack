package domain.participants

import domain.card.Cards
import domain.judge.Result

class Player(val name: String, val ownCards: Cards, val bettingMoney: Int) {
    lateinit var result: Result
        private set

    fun setResult(result: Result) {
        this.result = result
    }
}
