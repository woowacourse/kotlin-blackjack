package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards

class RandomCardFactory : CardFactory {
    private val cards: Cards = CardType.values()
        .zip(CardNumber.values())
        .map { Card(it.first, it.second) }
        .shuffled()
        .toMutableList()
        .let { Cards(it) }
    private var index = 0

    override fun generate(): Card = cards.value[index++]
}
