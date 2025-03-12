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

    fun playGame(
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        participants.players.forEach { player ->
            while (player.isDrawable()) {
                val choice = getPlayerChoice(player.name)
                when (choice) {
                    UserChoice.HIT -> player.addCard(deck.draw())
                    UserChoice.STAY -> {
                        if (player.getCards().size == INITIAL_CARD_COUNT) {
                            onPlayerStateUpdated(player)
                        }
                        break
                    }
                }
                onPlayerStateUpdated(player)
            }
        }
    }

    fun processDealerTurn(): Int {
        var count = 0
        while (participants.dealer.isDrawable()) {
            participants.dealer.addCard(deck.draw())
            count++
        }
        return count
    }

    fun calculateDealerResult(): Map<GameResult, Int> {
        val dealerMap = GameResult.entries.associateWith { 0 }.toMutableMap()

        participants.players.forEach { player ->
            val result = GameResult.resultOfDealer(participants.dealer.totalScore(), player.totalScore())
            dealerMap[result] = dealerMap.getOrDefault(result, 0) + 1
        }
        return dealerMap
    }

    fun calculatePlayerResult(action: (String, GameResult) -> Unit) {
        participants.players.forEach { player ->
            val result = GameResult.resultOfPlayer(participants.dealer.totalScore(), player.totalScore())
            action(player.name, result)
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
        const val CARD_COUNT_OF_PLAYER_MUST_INITIAL_OPEN = 2
        const val CARD_COUNT_OF_DEALER_MUST_INITIAL_OPEN = 1
        const val BUST_STANDARD = 21
    }
}
