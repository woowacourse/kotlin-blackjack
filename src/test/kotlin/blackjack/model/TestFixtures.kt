package blackjack.model

import blackjack.model.card.Card

val SPADE_ACE = Card.of("A", "스페이드")
val SPADE_FIVE = Card.of("5", "스페이드")
val SPADE_TEN = Card.of("10", "스페이드")
val HEART_THREE = Card.of("3", "하트")
val HEART_SEVEN = Card.of("7", "하트")
val HEART_KING = Card.of("K", "하트")
val DIAMOND_TWO = Card.of("2", "다이아몬드")
val DIAMOND_NINE = Card.of("9", "다이아몬드")

const val DEFAULT_BATTING_AMOUNT = 1000
