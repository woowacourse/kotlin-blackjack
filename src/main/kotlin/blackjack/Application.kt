package blackjack

import blackjack.controller.BlackjackController
import blackjack.model.card.RandomCardProvider
import blackjack.view.CardDecisionInputView
import blackjack.view.OutputView
import blackjack.view.PlayersBattingAmountInputView
import blackjack.view.PlayersNameInputView

fun main() {
    val blackjackController =
        BlackjackController(
            PlayersNameInputView(),
            PlayersBattingAmountInputView(),
            CardDecisionInputView(),
            OutputView(),
            RandomCardProvider,
        )
    blackjackController.run()
}
