package blackjack.fixture

import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.model.card.Rank
import blackjack.state.State

fun createBlackjackState(hand: Hand = Hand(createCard(rank = Rank.ACE), createCard(rank = Rank.TEN))) = State.Finish.BlackJack(hand)

fun createBustState(
    hand: Hand =
        Hand(
            createCard(rank = Rank.TEN),
            createCard(rank = Rank.TEN),
            createCard(rank = Rank.TEN),
        ),
) = State.Finish.Bust(hand)

fun createStopState(
    vararg cards: Card =
        arrayOf(
            createCard(rank = Rank.TEN),
            createCard(rank = Rank.TEN),
        ),
) = State.Finish.Stop(Hand(*cards))
