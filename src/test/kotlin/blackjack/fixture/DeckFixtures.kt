package blackjack.fixture

import blackjack.model.card.Card
import blackjack.model.card.Deck

fun createDeck(vararg cards: Card) = Deck(cards.toMutableList())
