package blackjack.fixture

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

fun participantsFixture() = Participants(listOf(Player("peto"), Player("bibi")) + Dealer())
