package domain.participants

import domain.card.Cards
import domain.judge.Result

class Player(val name: String, val ownCards: Cards) {
    var bettingMoney: Int = 0
        private set
    lateinit var result: Result
        private set

    fun setResult(result: Result) {
        this.result = result
    }

    fun setBettingMoney(money: Int) {
        this.bettingMoney = money
    }
}
