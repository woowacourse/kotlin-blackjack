package blackjack.domain

import blackjack.domain.deck.Deck
import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

class BlackJackGame(
    private val participants: Participants,
    private val deck: Deck,
) {
    fun handOutInitializedCards(initializedCardCount: Int = INITIAL_CARD_COUNT) {
        (participants.players + participants.dealer).forEach { player ->
            repeat(initializedCardCount) {
                player.receiveCard(deck.pop())
            }
        }
    }

    fun playGame(
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        participants.getChoice(deck, getPlayerChoice, onPlayerStateUpdated)
    }

    fun processDealerTurn(): Int {
        var count = 0
        while (participants.dealer.isOverMaxScore().not()) {
            participants.dealer.receiveCard(deck.pop())
            count++
        }
        return count
    }

    fun calculateDealerResult(action: (Map<GameResult, Int>) -> Unit) {
        val dealerMap = GameResult.entries.associateWith { 0 }.toMutableMap()

        participants.players.forEach { player ->
            val result = player.getResult(participants.dealer)
            dealerMap[result] = dealerMap.getOrDefault(result, 0) + 1
        }
        action(dealerMap)
    }

    fun calculatePlayerResult(action: (String, GameResult) -> Unit) {
        participants.players.forEach { player ->
            val result = player.getResult(participants.dealer)
            action(player.name, result)
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
        const val BUST_STANDARD = 21
    }
}
