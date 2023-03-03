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

    private fun Player.playerSelectAdd(input: (Name) -> Answer, output: (Player) -> Unit) {
        if (isBurst()) return
        val choice = input(name)
        if (choice == Answer.YES) addCard()
        output(this)
        if (choice == Answer.YES) playerSelectAdd(input, output)
    }

    fun playersSelectAddPhase(input: (Name) -> Answer, output: (Player) -> Unit) {
        players.players.forEach { player ->
            player.playerSelectAdd(input, output)
        }
    }

    fun dealerSelectPhase(output: (Dealer) -> Unit) {
        if (participants.dealer.isPossibleDrawCard()) {
            output(dealer)
            participants.dealer.addCard()
        }
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
