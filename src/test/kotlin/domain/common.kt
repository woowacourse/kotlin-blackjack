package domain

import domain.card.Card

fun Cards(vararg card: Card) = domain.card.Cards(listOf(*card))
fun Players(vararg player: Player) = Players(listOf(*player))
fun Players(size: Int, initPlayer: () -> Player): Players = Players(List(size) { initPlayer() })
