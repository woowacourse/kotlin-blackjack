package blackjack.fixture

import blackjack.domain.BettingAmount
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player
import blackjack.domain.participant.PlayerState

fun participantsFixture() =
    Participants(
        listOf(
            Player(PlayerState("peto", BettingAmount(1))),
            Player(PlayerState("bibi", BettingAmount(1))),
            Dealer(),
        ),
    )
