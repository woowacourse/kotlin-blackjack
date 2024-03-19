package blackjack.model

import blackjack.model.participant.Player
import blackjack.model.participant.state.Finish

data class Result(val player: Player, val finish: Finish)
