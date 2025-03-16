package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.participant.Player
import java.lang.String.format

class BlackJackTable(
    val deck: Deck,
    private val bettingMoney: Map<Player, Money>,
) {
    fun getPlayerBettingMoney(player: Player): Money =
        requireNotNull(bettingMoney[player]) { format(ERROR_NO_BETTING_MONEY_MESSAGE, player.name) }

    companion object {
        const val ERROR_NO_BETTING_MONEY_MESSAGE = "해당 플레이어 %s의 배팅 금액 정보가 없습니다."
    }
}
