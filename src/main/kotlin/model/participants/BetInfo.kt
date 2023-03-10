package model.participants

data class BetInfo(val info: Map<Player, Bet>) {
    operator fun get(player: Player): Bet? = info[player]
}
