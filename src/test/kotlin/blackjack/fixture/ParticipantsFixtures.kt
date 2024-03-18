package blackjack.fixture

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit

fun createPlayer(
    name: String = "송둥",
    hand: Hand,
) = Player(name, hand)

fun createPlayer(vararg cards: Card) = Player("송둥", Hand(*cards))

fun createDealer(vararg cards: Card) = Dealer(Hand(*cards))

fun createBlackJackPlayer(): Player {
    return createPlayer(
        createCard(Suit.SPADE, Rank.KING),
        createCard(Suit.SPADE, Rank.ACE),
    )
}
