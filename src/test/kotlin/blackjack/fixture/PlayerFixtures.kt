package blackjack.fixture

import blackjack.model.Card
import blackjack.model.HandCards
import blackjack.model.Player

fun createPlayer(
    name: String = "송둥",
    handCards: HandCards,
) = Player(name, handCards)

fun createPlayer(vararg cards: Card) = Player("송둥", HandCards(*cards))
