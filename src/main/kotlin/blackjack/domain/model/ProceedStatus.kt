package blackjack.domain.model

import blackjack.domain.model.participant.Participants

class ProceedStatus(
    val participant: Participants,
    val proceed: Proceed,
)
