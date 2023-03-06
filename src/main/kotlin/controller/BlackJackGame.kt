package controller

import domain.*

class BlackJackGame(names: Names, private val cardDrawer: CardDrawer = RandomCardDrawer()) {
    val participants: Participants
    private val players: Players
        get() = participants.players
    private val dealer: Dealer
        get() = participants.dealer

    init {
        val players = names.names.map { Player(it, cardDrawer.drawInitCards()) }
        val dealer = Dealer(cardDrawer.drawInitCards())
        participants = Participants(Players(players), dealer)
    }

    private fun Participant.addCard() {
        this.cards.add(cardDrawer.draw())
    }

    private fun Player.playerSelectAdd(getChoiceOfAddCard: (Player) -> Boolean, result: (Player) -> Unit) {
        val isGetCard = getChoiceOfAddCard(this)
        if (isGetCard) {
            addCard()
        }
        if(isBurst()) return
        result(this)
        if (isGetCard) playerSelectAdd(getChoiceOfAddCard, result)
    }

    fun playersSelectAddPhase(isGetCard: (Player) -> Boolean, output: (Player) -> Unit) {
        players.players.forEach { player ->
            player.playerSelectAdd(isGetCard, output)
        }
    }

    fun dealerSelectPhase(result: (Dealer) -> Unit) {
        if (participants.dealer.isPossibleDrawCard()) {
            result(dealer)
            participants.dealer.addCard()
        }
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
