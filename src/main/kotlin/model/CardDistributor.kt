package model

import entity.Cards

class CardDistributor(private val cardFactory: CardFactory) {
    fun distribute(count: Int): Cards {
        return Cards((0 until count).map { cardFactory.generate() })
    }
}
