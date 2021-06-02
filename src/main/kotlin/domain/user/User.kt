package domain.user

import domain.card.Cards
import domain.status.Status

interface User {
    val name: String
    val hand: Cards
    val status: Status
    fun draw(cards: Cards)
    fun changeStatus()
    fun score(): Int
}
