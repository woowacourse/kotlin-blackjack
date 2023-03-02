package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards

class RandomCardFactory : CardFactory {
    private val cards: Cards = CardType.values()
        .flatMap { CardNumber.values().map { it2 -> Card(it, it2) } }
        .shuffled()
        .toMutableList()
        .let { Cards(it) }

    private var index = 0

    override fun generate(): Card = cards.value[index++]
}
