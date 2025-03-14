package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck

fun generateCustomCards(cards: List<CardNumber> = listOf()): List<Card> {
    return cards.map { Card.create(it, CardPattern.HEART) }
}

fun generateCustomDeck(cards: List<CardNumber> = listOf()): Deck {
    val customCards = generateCustomCards(cards)
    val autoCards = List(52 - cards.size) { Card.create(CardNumber.TWO, CardPattern.HEART) }

    return Deck((customCards + autoCards))
}
