package domain

import domain.card.Card

class User(val name: String, val cards: Cards, val betAmount: Double) {

    lateinit var gameResult: GameResult

    fun addCard(card: Card) = cards.addCard(card)

    val score get() = cards.calculateCardValueSum()

    companion object {
        fun create(userBetAmount: UserBetAmount, cards: Cards): User =
            User(userBetAmount.userName, cards, userBetAmount.betAmount.toDouble())
    }
}
