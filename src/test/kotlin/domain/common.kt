package domain

import domain.card.Card

fun Cards(vararg card: Card) = domain.card.Cards(listOf(*card))
fun Player(name: String, betMoney: Int) = Player(PlayerInfo(name, betMoney))
fun Players(vararg player: Player) = Players(listOf(*player))
fun Players(size: Int, initPlayer: () -> Player): Players = Players(List(size) { initPlayer() })
fun PlayerInfo(name: String, betMoney: Int): PlayerInfo = PlayerInfo(Name(name), BetMoney(betMoney))
