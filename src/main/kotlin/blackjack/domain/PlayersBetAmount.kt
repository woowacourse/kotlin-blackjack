package blackjack.domain

data class PlayersBetAmount(private val playersBetAmount: Map<Player, Money>) {

    val players: Players = Players(playersBetAmount.keys)
    operator fun get(player: Player): Money? = playersBetAmount[player]
}
