package blackjack.controller

import blackjack.model.BlackJackGame
import blackjack.model.Dealer
import blackjack.model.GameDeck
import blackjack.model.Participants
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.OutputView.printDealerDrawCard
import blackjack.view.OutputView.showPlayerCards

class GameController {
    fun run() {
        val participantsNames = InputView.inputParticipantsNames()
        val participants = Participants()
        // InputView 로부터 참가자 이름 입력 받기
        participants.addPlayer(participantsNames)
        // 블랙잭 게임 시작
        val gameDeck = GameDeck()
        val blackJackGame = BlackJackGame(Dealer(), participants)

        blackJackGame.drawTwoCards(gameDeck)

        OutputView.printGameSetting(blackJackGame.dealer, blackJackGame.participants)

        blackJackGame.drawPlayerCard(
            gameDeck = gameDeck,
            input = { player ->
                // InputView 로부터 카드를 더 받을지 입력 받기
                val hitOrStay = InputView.askHitOrStand(player)
                when (hitOrStay) {
                    "y" -> true
                    "n" -> false
                    else -> false
                }
            },
            output = ::showPlayerCards,
        )

        blackJackGame.drawDealerCard(
            gameDeck = gameDeck,
            output = ::printDealerDrawCard,
        )

        OutputView.printEveryCards(blackJackGame.dealer, blackJackGame.participants)

        blackJackGame.matchResult()

        OutputView.printMatchResult(blackJackGame.dealer, blackJackGame.participants)
    }
}
