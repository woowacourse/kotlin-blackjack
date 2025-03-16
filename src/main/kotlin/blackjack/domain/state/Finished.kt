package blackjack.domain.state

import blackjack.domain.Hand

sealed class Finished : ParticipantState()

class Blackjack(
    override val hand: Hand,
) : Finished()

class Busted(
    override val hand: Hand,
) : Finished()

class Stay(
    override val hand: Hand,
) : Finished()
