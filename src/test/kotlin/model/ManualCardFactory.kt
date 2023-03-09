package model

import entity.card.Card
import entity.card.CardNumber
import entity.card.CardType
import entity.card.Cards

class ManualCardFactory(private val cardTypes: List<CardType>, private val cardNumbers: List<CardNumber>) :
    CardFactory {
    private var index = 0

    private fun generateCard(): Card {
        return Card(cardTypes[index], cardNumbers[index++])
    }

    override fun generate(count: Int): Cards {
        return Cards((0 until count).map { generateCard() })
    }
}
