package blackjack.domain

import blackjack.domain.participant.Participants
import blackjack.domain.participant.Player

class BlackJackGame(
    private val participants: Participants,
    private val deck: Deck,
) {
    fun handOutInitializedCards(initializedCardCount: Int = INITIAL_CARD_COUNT) {
        (participants.players + participants.dealer).forEach { player ->
            repeat(initializedCardCount) {
                player.addCard(deck.draw())
            }
        }
    }

    fun playerTurn(
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        participants.players.forEach { player ->
            processPlayerTurn(player, getPlayerChoice, onPlayerStateUpdated)
        }
    }

    private fun processPlayerTurn(
        player: Player,
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        while (player.canHit()) {
            val choice = getPlayerChoice(player.name)
            when (choice) {
                UserChoice.HIT -> player.addCard(deck.draw())
                UserChoice.STAY -> {
                    if (player.cards.items.size == INITIAL_CARD_COUNT) {
                        onPlayerStateUpdated(player)
                    }
                    break
                }
            }
            onPlayerStateUpdated(player)
        }
    }

    fun processDealerTurn(): Int {
        var count = 0
        while (participants.dealer.canHit()) {
            participants.dealer.addCard(deck.draw())
            count++
        }
        return count
    }

    fun calculateDealerResult(): Map<GameResult, Int> {
        val dealerMap = GameResult.entries.associateWith { 0 }.toMutableMap()

//        participants.players.forEach { player ->
//            val result = GameResult.from(participants.dealer.totalScore(), player.totalScore(), false)
//            dealerMap[result] = dealerMap.getOrDefault(result, 0) + 1
//        }
        return dealerMap
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
        const val CARD_COUNT_OF_PLAYER_MUST_INITIAL_OPEN = 2
        const val CARD_COUNT_OF_DEALER_MUST_INITIAL_OPEN = 1
        const val BUST_STANDARD = 21
    }
}
