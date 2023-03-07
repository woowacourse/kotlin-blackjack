package model

data class BetInfos(val infos: Map<Player, Bet>) {
    operator fun get(player: Player): Bet? = infos[player]
}
