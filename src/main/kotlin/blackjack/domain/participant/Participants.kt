package blackjack.domain.participant

import blackjack.domain.BettingAmount
import blackjack.domain.Profit
import blackjack.domain.card.Deck

class Participants(
    val dealer: Dealer,
    val players: List<Player>,
) {
    init {
        require(players.size in MIN_PLAYER_COUNT..MAX_PLAYER_COUNT) { ERROR_INVALID_PLAYER_COUNT }
    }

    fun drawCard(deck: Deck) {
        dealer.drawCard(deck.pick())
        players.forEach {
            it.drawCard(deck.pick())
        }
    }

    fun playGame(
        deck: Deck,
        onPlayerResponse: (Player) -> Boolean,
        onPlayerDraw: (Player) -> Unit,
        onDealerDraw: (Dealer) -> Unit,
    ) {
        playPlayersTurn(deck, onPlayerResponse, onPlayerDraw)
        playDealerTurn(deck, onDealerDraw)
    }

    private fun playPlayersTurn(
        deck: Deck,
        onResponse: (Player) -> Boolean,
        onDraw: (Player) -> Unit,
    ) {
        players.forEach { player ->
            while (player.canHit() && onResponse(player)) {
                player.drawCard(deck.pick())
                onDraw(player)
            }
        }
    }

    private fun playDealerTurn(
        deck: Deck,
        onDraw: (Dealer) -> Unit,
    ) {
        while (dealer.canHit()) {
            dealer.drawCard(deck.pick())
            onDraw(dealer)
        }
    }

    fun getDealerProfit(bettingInfo: Map<Player, BettingAmount>): Profit =
        Profit(
            players.sumOf { player ->
                val bettingAmount = bettingInfo.getOrDefault(player, BettingAmount(0))
                dealer.getProfit(player, bettingAmount).value
            },
        )

    fun getPlayersProfit(bettingInfo: Map<Player, BettingAmount>): Map<Player, Profit> =
        players.associateWith { player ->
            val bettingAmount = bettingInfo.getOrDefault(player, BettingAmount(0))
            player.getProfit(dealer, bettingAmount)
        }

    companion object {
        private const val MIN_PLAYER_COUNT = 1
        private const val MAX_PLAYER_COUNT = 7

        private const val ERROR_INVALID_PLAYER_COUNT = "1~7명의 플레이어가 참여할 수 있습니다."
    }
}
