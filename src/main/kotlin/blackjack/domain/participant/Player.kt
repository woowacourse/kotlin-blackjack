package blackjack.domain.participant

import blackjack.domain.ParticipantCards

class Player(
    val name: String,
    cards: ParticipantCards,
) : Participant(cards)
