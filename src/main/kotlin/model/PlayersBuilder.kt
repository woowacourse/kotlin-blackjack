package model

class PlayersBuilder {
    private val players = mutableListOf<Player>()

    fun player(block: PlayerBuilder.() -> Unit) {
        players.add(PlayerBuilder().apply(block).build())
    }

    fun player(player: Player) {
        players.add(player)
    }

    fun build() = Players(players.toList())
}
