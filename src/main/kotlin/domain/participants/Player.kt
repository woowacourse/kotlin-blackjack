package domain.participants

import domain.card.Cards
import domain.judge.Result

class Player(val name: String, val ownCards: Cards) {
    var bettingMoney: Int = 0
        private set
    lateinit var result: Result
        private set

    fun checkBurst(): Boolean = ownCards.calculateCardSum() > Dealer.CARD_SUM_MAX_VALUE

    fun setResult(result: Result) {
        this.result = result
    }
}
