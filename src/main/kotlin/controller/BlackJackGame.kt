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
    private lateinit var participants: Participants

    val players: Players
        get() = participants.players
    val dealer: Dealer
        get() = participants.dealer
    val all: List<Participant>
        get() = participants.all

    init {
        setPlayers(names)
    }

    fun setPlayers(names: Names) {
        val players = names.values.map { Player(it, deck.drawInitCards()) }
        val dealer = Dealer(deck.drawInitCards())
        participants = Participants(Players(players), dealer)
    }

    fun playersSelectAddPhase(isPlayerCardAdd: (Name) -> Boolean, printPlayerCards: (Player) -> Unit) {
        players.list.forEach { player ->
            player.playerSelectAdd(isPlayerCardAdd, printPlayerCards)
        }
    }

    private fun Player.playerSelectAdd(isPlayerCardAdd: (Name) -> Boolean, printPlayerCards: (Player) -> Unit) {
        if (isBurst()) return
        val isAddCard = isPlayerCardAdd(name)
        if (isAddCard) addCard()
        printPlayerCards(this)
        if (isAddCard) playerSelectAdd(isPlayerCardAdd, printPlayerCards)
    }

    private fun Participant.addCard() {
        addCard(deck.draw())
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
