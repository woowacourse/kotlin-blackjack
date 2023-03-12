package domain

import domain.card.Deck
import domain.money.Profit
import domain.person.Participants
import domain.person.Player
import domain.result.GameProfit

class BlackJackGame(private val deck: Deck, private val participants: Participants) {

    fun handOutCardsInitial(printInitialSetting: (Participants) -> Unit) {
        participants.dealer.receiveCard(deck.getCards(INITIAL_CARDS_COUNT))
        participants.players.forEach { it.receiveCard(deck.getCards(INITIAL_CARDS_COUNT)) }
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
        player.receiveCard(deck.getCards(GAME_CARD_COUNT))
        printPlayerCards(player)
    }

    fun handOutCardsToDealer(printDealerGetCardOrNot: (Boolean) -> Unit) {
        if (participants.dealer.isStarted()) {
            participants.dealer.receiveCard(deck.getCards(GAME_CARD_COUNT))
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

    companion object {
        const val INITIAL_CARDS_COUNT = 2
        const val GAME_CARD_COUNT = 1
    }
}
