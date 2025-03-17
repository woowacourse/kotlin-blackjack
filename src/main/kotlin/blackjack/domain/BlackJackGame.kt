package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

class BlackJackGame(
    private val participants: Participants,
    private val deck: Deck,
) {
    fun handOutInitializedCards(initializedCardCount: Int = INITIAL_CARD_TAKE_COUNT) {
        (participants.players + participants.dealer).forEach { player ->
            repeat(initializedCardCount) {
                player.receiveCard(deck.pop())
            }
        }
    }

    fun processPlayerTurn(
        getPlayerChoice: (String) -> Boolean,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        participants.getChoice(deck, getPlayerChoice, onPlayerStateUpdated)
    }

    fun processDealerTurn(): Int {
        var count = 0
        while (participants.dealer.isDrawable()) {
            participants.dealer.receiveCard(deck.pop())
            count++
        }
        return count
    }

    fun getDealerProfit(): Double =
        participants.players.sumOf { player ->
            val result = participants.dealer.getResult(player)
            result.calculateDealerProfit(player.money)
        }

    fun getPlayerProfit(action: (String, Double) -> Unit) {
        participants.players.forEach { player ->
            val result = player.getResult(participants.dealer)
            val profit = result.calculatePlayerProfit(player.money)
            action(player.name, profit)
        }
    }

    companion object {
        private const val INITIAL_CARD_TAKE_COUNT = 2
    }
}
