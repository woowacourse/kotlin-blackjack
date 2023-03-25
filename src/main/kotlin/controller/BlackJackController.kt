package controller

import model.domain.BlackJackGame
import model.domain.card.Card
import model.domain.card.Deck
import model.tools.Participant
import view.GameResultView
import view.PlayGameView

class BlackJackController {

    fun run() {
        val participants = getParticipants(PlayGameView::requestPlayerName)

        BlackJackGame(Deck.create(Card.deck.shuffled()), participants).apply {
            start(this)
            play(this)
            end(this)
        }
    }

    private fun start(blackJackGame: BlackJackGame) {
        blackJackGame.getBettingMoney(PlayGameView::requestPlayerBettingMoney)
        blackJackGame.provideCardToPlayer(PlayGameView::printNoticeSplitCard)
    }

    private fun play(blackJackGame: BlackJackGame) {
        blackJackGame.userPlay(PlayGameView::requestMoreCardIfWant, PlayGameView::noticeUserHandCard)
        blackJackGame.dealerPlay(PlayGameView::printDealerPickNewCard)
    }

    private fun end(blackJackGame: BlackJackGame) {
        blackJackGame.finishTurn(GameResultView::printFinalResult)
        blackJackGame.getProfit(GameResultView::printFinalProfit)
    }

    private fun getParticipants(getUsers: () -> List<String>): Participant = Participant.from(getUsers)
}
