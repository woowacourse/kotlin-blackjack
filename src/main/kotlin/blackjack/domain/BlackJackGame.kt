package blackjack.domain

import blackjack.domain.enums.UserChoice
import blackjack.domain.participant.Dealer
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

    fun processDealerTurn(): Int {
        var count = 0
        val dealer: Dealer = players.filterIsInstance<Dealer>().first()
        while (dealer.isOverMaxScore().not()) {
            dealer.addCard(deck.pop())
            count++
        }
        return count
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2
        const val BUST_STANDARD = 21
    }
}
