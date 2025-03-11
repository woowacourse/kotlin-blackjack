package blackjack.fakeParticipant

import blackjack.domain.participant.Participant

class FakeParticipant : Participant() {
    override val hitThreshold: Int
        get() = 21
}
