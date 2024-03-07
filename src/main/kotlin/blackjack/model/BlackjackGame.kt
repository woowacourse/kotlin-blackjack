package blackjack.model

class BlackjackGame(private val deck: CardDeck, val participants: Participants) {
    fun playRound(
        askForPlayerAction: (name: String) -> Boolean,
        displayParticipantsStatus: (Participant) -> Unit,
    ) {
        playRoundForPlayers(askForPlayerAction, displayParticipantsStatus)
        playRoundForDealer(displayParticipantsStatus)
    }

    private fun playRoundForPlayers(
        askForPlayerAction: (name: String) -> Boolean,
        displayParticipantsStatus: (player: Player) -> Unit,
    ) {
        participants.players.forEach { player ->
            while (player.state is Running) {
                val continuePlaying = askForPlayerAction(player.name)

                if (continuePlaying) {
                    player.receiveCard(deck.pick())
                } else {
                    player.finishRound()
                }
                displayParticipantsStatus(player)
            }
        }
    }

    private fun playRoundForDealer(displayParticipantsStatus: (dealer: Dealer) -> Unit) {
        val dealer = participants.dealer
        while (dealer.state is Running) {
            val continuePlaying = dealer.isUnderHitThreshold()
            if (continuePlaying) {
                dealer.receiveCard(deck.pick())
            } else {
                dealer.finishRound()
            }
            displayParticipantsStatus(dealer)
        }
    }
}
