package blackjack.domain.gameResult

import blackjack.domain.card.CardsState
import blackjack.domain.player.Player

data class PlayerGameResult(
    val playerName: String,
    val profitMoney: ProfitMoney,
) {

    companion object {

        fun of(player: Player, otherCardsState: CardsState) = PlayerGameResult(
            playerName = player.name.value,
            profitMoney = ProfitMoney.of(
                player.battingMoney,
                GameResult.valueOf(player.cards.state, otherCardsState)
            )
        )
    }
}
