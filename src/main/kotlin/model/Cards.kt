package model

import util.RandomShuffler
import util.Shuffler

class Cards(allCards: List<Card>,private val shuffler: Shuffler = RandomShuffler() ) {
    private val _allCards: MutableList<Card> = allCards.toMutableList()
    val allCards: List<Card> get() = _allCards.toList()

    init {
        require(allCards.distinct().size == _allCards.size) { DUPLICATE_CARD_ERROR_MESSAGE }
    }

    fun drawCards(count: Int): List<Card> {
        require(_allCards.size >= count) { REMAINING_CARD_ERROR_MESSAGE }

        val drawnCards = _allCards.take(count)
        _allCards.removeAll(drawnCards.toSet())

        return drawnCards
    }

    companion object {
        private const val DUPLICATE_CARD_ERROR_MESSAGE = "[ERROR] 카드는 중복될 수 없습니다"
        private const val REMAINING_CARD_ERROR_MESSAGE = "남아있는 카드의 수가 부족합니다"
    }
}
