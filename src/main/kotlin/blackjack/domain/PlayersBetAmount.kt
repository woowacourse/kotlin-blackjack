package blackjack.domain

data class PlayersBetAmount(private val playersBetAmount: Map<Player, Money>) {

    operator fun get(player: Player): Money? = playersBetAmount[player]
}
