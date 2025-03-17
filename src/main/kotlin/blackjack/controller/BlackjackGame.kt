package blackjack.controller

import blackjack.model.BettingMoney
import blackjack.model.Card
import blackjack.model.CardDeck
import blackjack.model.CardsStatus.Companion.FIRST_TURN_CARD_COUNT
import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.Player.Behavior
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val cardDeck = CardDeck(Card.wholeCards)

    fun run() {
        outputView.printStartMessage()
        val participants = Participants(players = inputView.readPlayers(), dealer = Dealer())

        getBettingMoney(participants.players)
        getCards(participants)
        playGames(participants)
    }

    private fun getBettingMoney(players: List<Player>) {
        players.forEach { player ->
            outputView.printBettingMessage(player)
            val money: BettingMoney = inputView.readBettingMoney()
            player.updateBettingMoney(money)
        }
    }

    private fun getCards(participants: Participants) {
        participants.pickCard(cardDeck, FIRST_TURN_CARD_COUNT)
        outputView.printParticipantCards(participants)
    }

    private fun playGames(participants: Participants) {
        if (handleDealerBlackjack(participants)) return
        participants.players.forEach { player ->
            executePlayerGame(player)
        }
        executeDealerGameLogic(participants.dealer)
        participants.updateProfit()
        displayResult(participants)
    }

    private fun handleDealerBlackjack(participants: Participants): Boolean {
        if (participants.dealer.isBlackjack()) {
            participants.updateProfit()
            outputView.printDealerBlackjack()
            displayResult(participants)
            return true
        }
        return false
    }

    private fun executePlayerGame(player: Player) {
        while (player.canHit()) {
            outputView.printPlayerBehaviorGuide(player)
            val playerBehavior: Behavior = inputView.readPlayerBehavior()

            if (isTurnOver(playerBehavior, player)) break
        }
    }

    private fun isTurnOver(
        playerBehavior: Behavior,
        player: Player,
    ): Boolean {
        when (playerBehavior) {
            Behavior.HIT -> if (isHitPlayerBust(player)) return true
            Behavior.STAY -> return true
        }
        return false
    }

    private fun isHitPlayerBust(player: Player): Boolean {
        player.pickCard(cardDeck)
        outputView.printPlayerCard(player)
        return isPlayerBust(player)
    }

    private fun isPlayerBust(player: Player): Boolean {
        if (player.isBust()) {
            outputView.printBust(player)
            return true
        }
        return false
    }

    private fun executeDealerGameLogic(dealer: Dealer) {
        while (dealer.canHit()) {
            dealer.pickCard(cardDeck)
            outputView.printDealerGettingCard()
            if (dealer.isBust()) break
        }
    }

    private fun displayResult(participants: Participants) {
        outputView.printResult(participants)
    }
}
