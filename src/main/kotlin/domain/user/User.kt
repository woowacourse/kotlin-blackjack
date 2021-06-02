package domain.user

import domain.card.Cards

interface User {
    val hand: Cards

    fun draw(cards: Cards)
    fun changeStatus()
}
