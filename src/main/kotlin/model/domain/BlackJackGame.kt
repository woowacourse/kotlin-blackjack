package model.domain

import model.domain.card.Deck
import model.domain.player.User
import model.domain.result.Profit
import model.domain.state.gameinprogress.Hit
import view.tools.Answer
import model.tools.Money
import model.tools.Participant

class BlackJackGame(private val deck: Deck, private val participants: Participant) {

    fun getBettingMoney(getBettingMoney: (User) -> Int) {
        participants.user.forEach { user ->
            val money = Money.from(getBettingMoney(user))
            user.betMoney(money)
            participants.dealer.betMoney(money)
        }
    }

    fun provideCardToPlayer(setPlayerCards: (Participant) -> Unit) {
        repeat(PAIR) {
            participants.user.forEach { user -> user.draw(deck) }
            participants.dealer.draw(deck)
        }

        setPlayerCards(participants)
    }

    fun userPlay(getCommand: (User) -> Answer, showUserHandCard: (User) -> Unit) {
        participants.user.forEach { user ->
            playGameInHit(user, getCommand, showUserHandCard)
        }
    }

    fun dealerPlay(noticeDealerIsNotOver17: () -> Unit) {
        participants.dealer.apply {
            if (state.hand.dealerIsNotOver17()) {
                noticeDealerIsNotOver17()
                draw(deck)
            }

            if ((state is Hit) and (state.hand.cards.size == THREE)) stay()
        }
    }

    fun finishTurn(getResult: (Participant) -> Unit) = getResult(participants)

    fun getProfit(showResult: (Participant) -> Unit) {
        Profit(participants).calculate()

        showResult(participants)
    }

    private fun playGameInHit(user: User, getCommand: (User) -> Answer, showUserHandCard: (User) -> Unit) {
        while (user.state is Hit) {
            val answer = getCommand(user).answer
            if (answer == NO) user.stay() else user.draw(deck)

            showUserHandCard(user)
        }
    }

    companion object {
        private const val PAIR = 2
        private const val THREE = 3
        private const val NO = "n"
    }
}
