package blackjack.domain

import blackjack.domain.enums.UserChoice
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

class BlackJackGame(
    private val players: List<Participant>,
    private val deck: Deck,
) {
    fun handOutInitializedCards(initializedCardCount: Int = INITIAL_CARD_COUNT) {
        players.forEach { player ->
            repeat(initializedCardCount) {
                player.addCard(deck.pop())
            }
        }
    }

    fun playGame(action: (String) -> UserChoice) {
        players
            .filterIsInstance<Player>()
            .forEach { player ->
                processPlayerTurn(player, action)
            }
    }

    private fun processPlayerTurn(
        player: Player,
        action: (String) -> UserChoice,
    ) {
        while (!player.isBust()) {
            val choice = action(player.name)
            when (choice) {
                UserChoice.HIT -> player.addCard(deck.pop())
                UserChoice.STAY -> break
            }
        }
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
        const val BUST_STANDARD = 21
    }
}
