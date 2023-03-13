package domain

import domain.card.Card
import domain.card.CardValue
import domain.card.Shape
import domain.gamer.cards.Cards

fun SpadeCardsOf(vararg cardValues: CardValue): Cards {
    return Cards(cardValues.map { Card.from(Shape.SPADE, it) })
}

fun HeartCardsOf(vararg cardValues: CardValue): Cards {
    return Cards(cardValues.map { Card.from(Shape.HEART, it) })
}

fun DiamondCardsOf(vararg cardValues: CardValue): Cards {
    return Cards(cardValues.map { Card.from(Shape.DIAMOND, it) })
}

fun CloverCardsOf(vararg cardValues: CardValue): Cards {
    return Cards(cardValues.map { Card.from(Shape.CLOVER, it) })
}
