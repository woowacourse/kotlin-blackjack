package domain

import domain.card.Deck
import domain.money.Profit
import domain.person.Participants
import domain.person.Person
import domain.person.Player
import domain.result.GameProfit
import domain.state.Started

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
        while (player.state is Started) {
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
        if (participants.dealer.state is Started) {
            participants.dealer.receiveCard(deck.getCards(GAME_CARD_COUNT))
            printDealerGetCardOrNot(true)
            return
        }
        printDealerGetCardOrNot(false)
    }

    fun judgeResult(
        gameProfit: GameProfit,
        printCardsResult: (Participants) -> Unit,
        printFinalResult: (Profit, Map<Person, Profit>) -> Unit,
    ) {
        printCardsResult(participants)
        val dealerProfit = gameProfit.getPersonProfitTotal(person = participants.dealer, others = participants.players)
        val playerProfit = gameProfit.getPersonsProfit(persons = participants.players, other = participants.dealer)
        printFinalResult(dealerProfit, playerProfit)
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
        const val GAME_CARD_COUNT = 1
    }
}
