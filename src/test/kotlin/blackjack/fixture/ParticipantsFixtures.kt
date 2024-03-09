package blackjack.fixture

import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player

fun createPlayer(
    name: String = "송둥",
    hand: Hand,
) = Player(name, hand)

fun createPlayer(vararg cards: Card) = Player("송둥", Hand(*cards))

fun createDealer(vararg cards: Card) = Dealer(Hand(*cards))
