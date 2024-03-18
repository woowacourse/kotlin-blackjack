package blackjack.fixture

import blackjack.model.Betting
import blackjack.model.participant.Player

private const val NAME = "송둥"

fun createBustPlayer(betting: Betting): Player = Player(NAME, betting, createBustState()) { true }
