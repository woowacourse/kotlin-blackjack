package study.self.personBuilder

import domain.card.Card
import domain.person.Dealer
import domain.person.Player

interface PersonBuilder {
    fun name(name: String)
    fun addTwoCards(card1: Card, card2: Card)
    fun buildDealer(): Dealer
    fun buildPlayer(): Player
}
