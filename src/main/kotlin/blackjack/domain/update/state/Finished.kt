package blackjack.domain.update.state

import blackjack.domain.update.NewHand

sealed class Finished : NewParticipantState()

class Blackjack(
    override val hand: NewHand,
) : Finished()

class Busted(
    override val hand: NewHand,
) : Finished()

class Stay(
    override val hand: NewHand,
) : Finished()
