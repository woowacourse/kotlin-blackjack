package blackjack.fixture

import blackjack.model.card.Card
import blackjack.model.card.Hand
import blackjack.model.card.Rank
import blackjack.state.State

fun createRunningState(vararg cards: Card = arrayOf(createCard(rank = Rank.TWO), createCard(rank = Rank.TEN))) = State.Running(Hand(*cards))

fun createRunningState(hand: Hand = Hand(createCard(rank = Rank.TWO), createCard(rank = Rank.TEN))) = State.Running(hand)

fun createBlackjackState(vararg cards: Card = arrayOf(createCard(rank = Rank.ACE), createCard(rank = Rank.TEN))) =
    State.Finish.BlackJack(Hand(*cards))

fun createBlackjackState(hand: Hand = Hand(createCard(rank = Rank.ACE), createCard(rank = Rank.TEN))) = State.Finish.BlackJack(hand)

fun createBustState(
    vararg cards: Card =
        arrayOf(
            createCard(rank = Rank.TEN),
            createCard(rank = Rank.TEN),
            createCard(rank = Rank.TEN),
        ),
) = State.Finish.Bust(Hand(*cards))

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

fun createStopState(
    hand: Hand =
        Hand(
            createCard(rank = Rank.TEN),
            createCard(rank = Rank.TEN),
        ),
) = State.Finish.Stop(hand)
