package blackjack.domain

import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

class BlackJackGame(
    private val participants: Participants,
    private val table: BlackJackTable,
) {
    fun handOutInitializedCards(initializedCardCount: Int = INITIAL_CARD_TAKE_COUNT) {
        (participants.players + participants.dealer).forEach { player ->
            repeat(initializedCardCount) {
                player.receiveCard(table.deck.pop())
            }
        }
    }

    fun processPlayerTurn(
        getPlayerChoice: (String) -> Boolean,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        participants.players.forEach { player ->
            while (player.isDrawable()) {
                if (getPlayerChoice(player.name)) {
                    player.receiveCard(table.deck.pop())
                    onPlayerStateUpdated(player)
                } else {
                    break
                }
            }
        }
    }

    fun processDealerTurn(): Int {
        var count = 0
        while (participants.dealer.isDrawable()) {
            participants.dealer.receiveCard(table.deck.pop())
            count++
        }
        return count
    }

    fun getDealerProfit(): Double =
        participants.players.sumOf { player ->
            val result = participants.dealer.getResult(player)
            result.calculateDealerProfit(table.getPlayerBettingMoney(player))
        }

    fun getPlayerProfit(action: (String, Double) -> Unit) {
        participants.players.forEach { player ->
            val result = player.getResult(participants.dealer)
            val profit = result.calculatePlayerProfit(table.getPlayerBettingMoney(player))
            action(player.name, profit)
        }
    }

    companion object {
        private const val INITIAL_CARD_TAKE_COUNT = 2
    }
}
