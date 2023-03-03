package domain

class BlackJackGame(names: Names, private val cardDrawer: CardDrawer = RandomCardDrawer()) {
    val participants: Participants
    val players: Players
        get() = participants.players

    val dealer: Dealer
        get() = participants.dealer

    init {
        val players = names.names.map { Player(it, cardDrawer.drawInitCards()) }
        val dealer = Dealer(cardDrawer.drawInitCards())
        participants = Participants(Players(players), dealer)
    }

    private fun Participant.addCard() {
        this.cards.add(cardDrawer.draw())
    }

    private fun Player.playerSelectAdd(input: (Player) -> String, output: (Player) -> Unit) {
        if (isBurst()) return
        val choice = input(this)
        if (choice == "y") addCard()
        output(this)
        if (choice == "y") playerSelectAdd(input, output)
    }

    fun playersSelectAddPhase(input: (Player) -> String, output: (Player) -> Unit) {
        players.players.forEach { player ->
            player.playerSelectAdd(input, output)
        }
    }

    fun dealerSelectPhase(output: () -> Unit) {
        if (participants.dealer.isPossibleDrawCard()) {
            output()
            participants.dealer.addCard()
        }
    }
}
