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

    private fun generateCard(): Card {
        require(index < cards.value.size) {
            MESSAGE_TOO_MANY_GENERATE_CARD
        }
        return cards.value[index++]
    }

    override fun generate(count: Int): Cards {
        return Cards((0 until count).map { generateCard() })
    }

    companion object {
        const val MESSAGE_TOO_MANY_GENERATE_CARD = "생성할 수 있는 카드의 수를 초과했습니다."
    }
}
