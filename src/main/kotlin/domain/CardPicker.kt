package domain

import model.Card
import model.Cards

class CardPicker(private val cards: Cards) {
    private var cursor = 0
    fun pick(): Card {
        require(cursor < cards.size) { OUT_OF_INDEX_CARDS_CURSOR }
        return cards.cards[cursor++]
    }

    companion object {
        private const val OUT_OF_INDEX_CARDS_CURSOR = "카드를 모두 사용했습니다."
    }
}
