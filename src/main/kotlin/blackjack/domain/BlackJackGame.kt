package blackjack.domain

import blackjack.domain.enums.UserChoice
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player

class BlackJackGame(
    private val participants: List<Participant>,
    private val deck: Deck,
) {
    private val dealer: Dealer = participants.filterIsInstance<Dealer>().first()
    private val players: List<Player> = participants.filterIsInstance<Player>()

    fun handOutInitializedCards(initializedCardCount: Int = INITIAL_CARD_COUNT) {
        participants.forEach { player ->
            repeat(initializedCardCount) {
                player.addCard(deck.pop())
            }
        }
    }

    fun playGame(
        getPlayerChoice: (String) -> UserChoice,
        onPlayerStateUpdated: (Player) -> Unit,
    ) {
        players.forEach { player ->
            while (!player.isBust()) {
                val choice = getPlayerChoice(player.name)
                when (choice) {
                    UserChoice.HIT -> player.addCard(deck.pop())
                    UserChoice.STAY -> {
                        if (player.cards.size == 2) {
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
