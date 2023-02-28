package model

import entity.Card
import entity.CardNumber
import entity.CardType

class ManualCardFactory(private val cardTypes: List<CardType>, private val cardNumbers: List<CardNumber>) : CardFactory {
    var index = 0

    override fun generate(): Card {
        return Card(cardTypes[index], cardNumbers[index++])
    }
}
