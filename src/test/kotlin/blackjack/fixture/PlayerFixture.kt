package blackjack.fixture

import blackjack.domain.ParticipantCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

fun playersFixture() = listOf(Player("peto", ParticipantCards()), Player("bibi", ParticipantCards())) + Dealer(ParticipantCards())
