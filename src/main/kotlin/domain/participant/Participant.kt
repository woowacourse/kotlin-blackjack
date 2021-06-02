package domain.participant

import domain.*
import domain.card.Card

abstract class Participant(val name: String, val hand: Hand = Hand(), money: Money = Money()) {
    var money : Money = money
        private set

    var isHitStatus : Boolean = true
        private set

    fun draw(card: Card, response: ResponseType?) {
        if (null != response && !response.drawable) {
            stay()
            return
        }
        hand.addCard(card)
        checkStatus(response ?: ResponseType.HIT)
    }

    fun score(): Int {
        return hand.getScore()
    }

    fun isBust(): Boolean {
        return hand.isBust()
    }

    fun isBlackJackScore(): Boolean {
        return hand.isBlackJackScore()
    }

    fun addMoney(value: Int) {
        this.money = this.money + value
    }

    fun calculateProfit(playerResult: ResultType): Int {
        return (
                this.money *
                        playerResult.profitRate).value
    }

    private fun stay() {
        this.isHitStatus = false
    }

    private fun checkStatus(response: ResponseType) {
        if (!response.drawable || !isDrawable()) {
            stay()
        }
    }

    abstract fun isDrawable(): Boolean

}
