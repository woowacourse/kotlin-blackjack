package blackjack.fixture

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player

fun playersFixture() = listOf(Player("peto"), Player("bibi")) + Dealer()
