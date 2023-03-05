package controller

import domain.BlackJackCardDeck
import domain.CardDeck
import domain.Dealer
import domain.Name
import domain.Names
import domain.Participant
import domain.Participants
import domain.Player
import domain.Players

class BlackJackGame(names: Names, private val deck: CardDeck = BlackJackCardDeck()) {
    val participants: Participants
    val players: Players
        get() = participants.players
    val dealer: Dealer
        get() = participants.dealer

    init {
        val players = names.names.map { Player(it, deck.drawInitCards()) }
        val dealer = Dealer(deck.drawInitCards())
        participants = Participants(Players(players), dealer)
    }

    private fun Participant.addCard() {
        addCard(deck.draw())
    }

    private fun Player.playerSelectAdd(isPlayerCardAdd: (Name) -> Boolean, printPlayerCards: (Player) -> Unit) {
        if (isBurst()) return
        val isAddCard = isPlayerCardAdd(name)
        if (isAddCard) addCard()
        printPlayerCards(this)
        if (isAddCard) playerSelectAdd(isPlayerCardAdd, printPlayerCards)
    }

    fun playersSelectAddPhase(isPlayerCardAdd: (Name) -> Boolean, printPlayerCards: (Player) -> Unit) {
        players.players.forEach { player ->
            player.playerSelectAdd(isPlayerCardAdd, printPlayerCards)
        }
    }

    fun dealerSelectPhase(printDealerAddCard: (Dealer) -> Unit) {
        if (dealer.isPossibleDrawCard()) {
            printDealerAddCard(dealer)
            dealer.addCard()
        }
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
