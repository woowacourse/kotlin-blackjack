package blackjack.model

class BlackjackGame(private val deck: CardDeck, val participants: Participants) {
    fun playRound(
        askForPlayerAction: (name: String) -> Boolean,
        displayParticipantsStatus: (Participant) -> Unit,
    ) {
        playRoundForPlayers(askForPlayerAction, displayParticipantsStatus)
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
}
