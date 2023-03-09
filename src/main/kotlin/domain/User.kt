package domain

import domain.card.Card

class User(val name: String, val cards: Cards, val betAmount: Double) {

    lateinit var gameResult: GameResult

    val score get() = Score.valueOfCards(cards.numbers)

    fun addCard(card: Card) = cards.addCard(card)

    companion object {
        fun create(userBetAmount: UserBetAmount, cards: Cards): User =
            User(userBetAmount.userName, cards, userBetAmount.betAmount.toDouble())
    }
}
