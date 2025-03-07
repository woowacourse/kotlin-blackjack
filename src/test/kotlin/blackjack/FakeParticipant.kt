package blackjack

import blackjack.domain.Participant

class FakeParticipant : Participant() {
    override val hitThreshold: Int
        get() = 21
}
