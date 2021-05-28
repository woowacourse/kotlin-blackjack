package domain.player

import domain.card.Card

open class Player(val name: String, val bettingMoney: Int = 0) {

    private var earningMoney = 0

    var cards = PlayerCards()
        private set

    fun receiveCard(card : Card){
        cards = cards.add(card)
    }

    fun giveMoney(other :Player, money :Int){
        this.earningMoney -= money
        other.earningMoney += money
    }

    fun takeMoney(other :Player, money :Int){
        giveMoney(other, -money)
    }

    fun isBlackJack(): Boolean {
        return cards.type.isBlackJack()
    }

    fun isWin(other: Player): Boolean {
        return this.cards.isWin(other.cards)
    }

    fun isLose(other: Player): Boolean {
        return this.cards.isLose(other.cards)
    }

    fun score(): Int {
        return cards.score.value
    }
}
