package domain

import domain.card.Deck
import domain.money.Profit
import domain.person.Participants
import domain.person.Player
import domain.result.GameProfit

class BlackJackGame(private val deck: Deck, private val participants: Participants) {

    fun handOutCardsInitial(printInitialSetting: (Participants) -> Unit) {
        participants.dealer.receiveCard(deck.getCards(2))
        participants.players.forEach { it.receiveCard(deck.getCards(2)) }
        printInitialSetting(participants)
    }

    fun handOutCardsToPlayers(
        getPlayerWantContinue: (String) -> Boolean,
        printPlayerCards: (Player) -> Unit,
    ) {
        participants.players.forEach { player -> handOutCardsToPlayer(player, getPlayerWantContinue, printPlayerCards) }
    }

    private fun handOutCardsToPlayer(
        player: Player,
        isContinue: (String) -> Boolean,
        printPlayerCards: (Player) -> Unit,
    ) {
        while (player.isStarted()) {
            handOutCard(player, isContinue, printPlayerCards)
        }
    }

    private fun handOutCard(player: Player, isContinue: (String) -> Boolean, printPlayerCards: (Player) -> Unit) {
        if (!isContinue(player.name)) {
            player.stay()
            return
        }
        player.receiveCard(deck.getCards(1))
        printPlayerCards(player)
    }

    fun handOutCardsToDealer(printDealerGetCardOrNot: (Boolean) -> Unit) {
        if (participants.dealer.isStarted()) {
            participants.dealer.receiveCard(deck.getCards(1))
            printDealerGetCardOrNot(true)
            return
        }
        printDealerGetCardOrNot(false)
    }

    fun judgeResult(
        gameProfit: GameProfit,
        printCardsResult: (Participants) -> Unit,
        printFinalResult: (Profit, Map<Player, Profit>) -> Unit,
    ) {
        printCardsResult(participants)
        val dealerProfit = gameProfit.getDealersProfit(participants)
        val playerProfit = gameProfit.getPlayersProfit(participants)
        printFinalResult(dealerProfit, playerProfit)
    }
}
