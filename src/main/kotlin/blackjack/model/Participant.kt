package blackjack.model

import blackjack.model.ParticipantInformation.DealerInformation
import blackjack.model.ParticipantInformation.PlayerInformation

sealed class Participant(val participantInformation: ParticipantInformation, val gameInformation: GameInformation) {
    fun draw(card: Card) {
        if (isRunning()) {
            gameInformation.drawCard(card)
        }
    }

    fun isRunning(): Boolean {
        return gameInformation.state is GameState.Running
    }

    class Player(val playerInformation: PlayerInformation, gameInformation: GameInformation = GameInformation()) :
        Participant(playerInformation, gameInformation) {
        fun judgeDrawOrNot(
            cardDeck: CardDeck,
            readDecision: () -> Boolean,
            output: () -> Unit,
        ) {
            while (isRunning()) {
                if (readDecision()) {
                    draw(cardDeck.pickCard())
                    output()
                } else {
                    gameInformation.changeStateToStay()
                }
            }
        }
    }

    class Dealer(
        dealerInformation: ParticipantInformation = DealerInformation(),
        gameInformation: GameInformation = GameInformation(),
    ) : Participant(dealerInformation, gameInformation) {
        fun initialCardDealing(
            players: List<Player>,
            cardDeck: CardDeck,
        ) {
            repeat(INITIAL_DEALING_COUNT) {
                draw(cardDeck.pickCard())
                players.forEach { participant ->
                    participant.draw(cardDeck.pickCard())
                }
            }
        }

        fun judgeDrawOrNot(
            cardDeck: CardDeck,
            output: () -> Unit,
        ) {
            while (Calculator.calculateScore(gameInformation.cards) <= ADDITIONAL_DRAW_CRITERIA) {
                draw(cardDeck.pickCard())
                output()
            }
            if (Calculator.calculateScore(gameInformation.cards) < BLACKJACK_SCORE) {
                gameInformation.changeStateToStay()
            }
        }

        companion object {
            private const val INITIAL_DEALING_COUNT = 2
            private const val ADDITIONAL_DRAW_CRITERIA = 16
            private const val BLACKJACK_SCORE = 21
        }
    }
}
