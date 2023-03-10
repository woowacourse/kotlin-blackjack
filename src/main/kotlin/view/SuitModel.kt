package view

import model.cards.Suit

class SuitModel {
    fun getString(suit: Suit) = when (suit) {
        Suit.DIAMOND -> DIAMOND
        Suit.CLOVER -> CLOVER
        Suit.SPADE -> SPADE
        Suit.HEART -> HEART
    }

    companion object {
        private const val DIAMOND = "다이아몬드"
        private const val CLOVER = "클로버"
        private const val SPADE = "스페이드"
        private const val HEART = "하트"
    }
}
