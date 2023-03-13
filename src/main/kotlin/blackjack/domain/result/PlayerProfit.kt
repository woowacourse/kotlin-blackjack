package blackjack.domain.result

import blackjack.domain.player.Player

data class PlayerProfit(val player: Player, val profit: Int)
