package domain.player

import domain.card.Card

open class Player(val name: String, val bettingMoney: Money = Money.ZERO) {

    var earningMoney = Money.ZERO
        private set

    var cards = PlayerCards()
        private set

    fun receiveCards(receivedCards: List<Card>) {
        cards = cards.add(receivedCards)
    }

    fun earn(money: Money) {
        earningMoney = earningMoney.earn(money)
    }

    fun lose(money: Money) {
        earningMoney = earningMoney.lose(money)
    }

    fun isBlackJack() = this.cards.isBlackJack()

    fun isWin(other: Player) = this.cards.isWin(other.cards)

    fun isLose(other: Player) = this.cards.isLose(other.cards)
}
