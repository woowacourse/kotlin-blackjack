package blackjack.model

sealed class Participant(val userInformation: UserInformation, val gameInformation: GameInformation) {
    fun draw(card: Card) {
        if (isRunning()) {
            gameInformation.drawCard(card)
        }
    }

    fun isRunning(): Boolean {
        return gameInformation.state is GameState.Running
    }

    class Player(userInformation: UserInformation, gameInformation: GameInformation = GameInformation()) :
        Participant(userInformation, gameInformation) {
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
        userInformation: UserInformation =
            UserInformation(
                ParticipantName(DEFAULT_DEALER_NAME),
                BettingAmount(DEFAULT_DEALER_BETTING_AMOUNT),
            ),
        gameInformation: GameInformation = GameInformation(),
    ) : Participant(userInformation, gameInformation) {
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
            while (ScoreCalculator.calculateScore(gameInformation.cards) <= ADDITIONAL_DRAW_CRITERIA) {
                draw(cardDeck.pickCard())
                output()
            }
            if (ScoreCalculator.calculateScore(gameInformation.cards) < BLACKJACK_SCORE) {
                gameInformation.changeStateToStay()
            }
        }

        companion object {
            private const val DEFAULT_DEALER_NAME = "딜러"
            private const val DEFAULT_DEALER_BETTING_AMOUNT = 1
            private const val INITIAL_DEALING_COUNT = 2
            private const val ADDITIONAL_DRAW_CRITERIA = 16
            private const val BLACKJACK_SCORE = 21
        }
    }
}
