package controller

import domain.Answer
import domain.CardDeck
import domain.Dealer
import domain.Name
import domain.Names
import domain.Participant
import domain.Participants
import domain.Player
import domain.Players
import domain.BlackJackCardDeck

class BlackJackGame(names: Names, private val cardDeck: CardDeck = BlackJackCardDeck()) {
    val participants: Participants
    private val players: Players
        get() = participants.players
    private val dealer: Dealer
        get() = participants.dealer

    init {
        val players = names.names.map { Player(it, cardDeck.drawInitCards()) }
        val dealer = Dealer(cardDeck.drawInitCards())
        participants = Participants(Players(players), dealer)
    }

    private fun Participant.addCard() {
        this.cards.add(cardDeck.draw())
    }

    private fun Player.playerSelectAdd(playerCardAddAnswer: (Name) -> Answer, printPlayerCards: (Player) -> Unit) {
        if (isBurst()) return
        val isAddCard = playerCardAddAnswer(name)
        if (isAddCard == Answer.YES) addCard()
        printPlayerCards(this)
        if (isAddCard == Answer.YES) playerSelectAdd(playerCardAddAnswer, printPlayerCards)
    }

    fun playersSelectAddPhase(playerCardAddAnswer: (Name) -> Answer, printPlayerCards: (Player) -> Unit) {
        players.players.forEach { player ->
            player.playerSelectAdd(playerCardAddAnswer, printPlayerCards)
        }
    }

    fun dealerSelectPhase(printDealerAddCard: (Dealer) -> Unit) {
        if (participants.dealer.isPossibleDrawCard()) {
            printDealerAddCard(dealer)
            participants.dealer.addCard()
        }
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
