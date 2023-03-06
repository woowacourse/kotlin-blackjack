package model

import entity.Card
import entity.CardNumber
import entity.CardType
import entity.Cards

class ManualCardFactory(private val cardTypes: List<CardType>, private val cardNumbers: List<CardNumber>) :
    CardFactory {
    private var index = 0

    private fun generateCard(): Card {
        require(index < cardTypes.size && index < cardNumbers.size) {
            MESSAGE_TOO_MANY_GENERATE_CARD
        }
        return Card(cardTypes[index], cardNumbers[index++])
    }

    override fun generate(count: Int): Cards {
        return Cards((0 until count).map { generateCard() })
    }

    companion object {
        const val MESSAGE_TOO_MANY_GENERATE_CARD = "생성할 수 있는 카드의 수를 초과했습니다."
    }
}
