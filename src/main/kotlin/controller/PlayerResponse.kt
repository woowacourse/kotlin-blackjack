package controller

import domain.player.Money
import domain.player.Player
import domain.player.PlayerCards

data class PlayerResponse(
    val name: String,
    val earningMoney: Money = Money.ZERO,
    val cards: PlayerCards = PlayerCards(listOf())
){
    constructor(player: Player) :this(player.name, player.earningMoney, player.cards)

    companion object{
        fun parseList(players : List<Player>):List<PlayerResponse>{
            return players.map {
                PlayerResponse(it)
            }
        }
    }
}