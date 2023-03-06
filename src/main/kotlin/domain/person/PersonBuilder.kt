package domain.person

import domain.card.Card

interface PersonBuilder {
    fun name(name: String)
    fun addTwoCards(card1: Card, card2: Card)
    fun buildDealer(): Dealer
    fun buildPlayer(): Player
}
