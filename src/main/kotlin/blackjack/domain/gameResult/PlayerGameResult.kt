package blackjack.domain.gameResult

import blackjack.domain.dealer.Dealer
import blackjack.domain.player.Player

data class PlayerGameResult(
    val playerName: String,
    val profitMoney: ProfitMoney,
) {

    companion object {

        fun of(player: Player, dealer: Dealer) = PlayerGameResult(
            playerName = player.name.value,
            profitMoney = ProfitMoney.of(
                player.battingMoney,
                GameResult.valueOf(player.cards, dealer.cards)
            )
        )
    }
}