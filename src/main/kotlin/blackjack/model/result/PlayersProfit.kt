package blackjack.model.result

import blackjack.model.role.PlayerName

data class PlayersProfit(val result: Map<PlayerName, Money>)
