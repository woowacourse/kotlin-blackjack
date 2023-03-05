package domain

import model.Card
import model.CardDeck

class CardPicker(private val cardDeck: CardDeck) {
    private var cursor = 0
    fun pick(): Card = cardDeck[cursor++]
}
