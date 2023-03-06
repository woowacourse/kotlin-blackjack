package domain

fun Cards(vararg card: Card) = Cards(listOf(*card))
fun Players(vararg player: Player) = Players(listOf(*player))
fun Players(size: Int, initPlayer: () -> Player): Players = Players(List(size) { initPlayer() })
