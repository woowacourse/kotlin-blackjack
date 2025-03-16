package blackjack.fixture

import blackjack.domain.ParticipantCards
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

fun playersFixture() =
    Participants(
        Dealer(ParticipantCards()),
        listOf(Player("peto", ParticipantCards()), Player("bibi", ParticipantCards())),
    )
