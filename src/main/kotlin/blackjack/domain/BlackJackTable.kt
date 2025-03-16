package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.participant.Player

class BlackJackTable(
    val deck: Deck,
    private val bettingMoney: Map<Player, Money>,
) {
    fun getPlayerBettingMoney(player: Player): Money = bettingMoney[player]!!
}
