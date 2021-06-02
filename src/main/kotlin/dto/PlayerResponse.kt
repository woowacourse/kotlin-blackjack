package dto

import domain.player.Money
import domain.player.Player
import domain.player.PlayerCards

data class PlayerResponse(
    val name: String,
    val earningMoney: Money = Money.ZERO,
    val cards: PlayerCards = PlayerCards(listOf())
) {
    constructor(player: Player) : this(player.name, player.earningMoney, player.cards)
}
