package domain.user

import domain.card.Cards

interface User {
    val name: String
    val hand: Cards

    fun draw(cards: Cards)
    fun changeStatus()
    fun score(): Int
}
