package blackjack.model.result

import blackjack.model.participant.PlayerName

class GameResultStorage(
    val dealerResult: Map<GameResultType, Int>,
    val playersResult: Map<PlayerName, GameResultType>,
)
