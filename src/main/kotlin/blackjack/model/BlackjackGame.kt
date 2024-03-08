package blackjack.model

class BlackjackGame(private val deck: CardDeck, val participants: Participants) {
    fun playRound(
        askForPlayerAction: (name: ParticipantName) -> Boolean,
        displayParticipantsStatus: (Participant) -> Unit,
    ) {
        playRoundForPlayers(askForPlayerAction, displayParticipantsStatus)
        playRoundForDealer(displayParticipantsStatus)
    }

    private fun playRoundForPlayers(
        askForPlayerAction: (name: ParticipantName) -> Boolean,
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

    fun calculateResult(): Map<Participant, WinningState> {
        val (dealer, players) = participants
        val playersResult = calculatePlayersResult(dealer, players)
        val dealerResult = calculateDealerResult(dealer, playersResult)
        return dealerResult.plus(playersResult)
    }

    private fun calculatePlayersResult(
        dealer: Dealer,
        players: List<Player>,
    ): Map<Participant, WinningState> {
        return players.associate { player ->
            player to
                when (dealer.state) {
                    is Blackjack -> whenDealerIsBlackjack(player)
                    is Bust -> whenDealerIsBust(player)
                    else -> whenDealerIsStay(player, dealer)
                }
        }
    }

    private fun calculateDealerResult(
        dealer: Dealer,
        outcomes: Map<Participant, WinningState>,
    ): Map<Participant, WinningState> {
        val dealerWinCount = outcomes.values.count { it.wins == 0 && it.losses == 1 }
        val dealerLoseCount = outcomes.values.count { it.wins == 1 && it.losses == 0 }
        return mapOf(dealer to WinningState(dealerWinCount, dealerLoseCount))
    }

    private fun whenDealerIsBlackjack(player: Player): WinningState {
        return when (player.state) {
            is Blackjack -> WinningState(0, 0)
            else -> WinningState(0, 1)
        }
    }

    private fun whenDealerIsBust(player: Player): WinningState {
        return when (player.state) {
            is Bust -> WinningState(0, 1)
            else -> WinningState(1, 0)
        }
    }

    private fun whenDealerIsStay(
        player: Player,
        dealer: Dealer,
    ): WinningState {
        val dealerScore = dealer.calculateHandSum()
        val playerScore = player.calculateHandSum()

        return when {
            player.state is Bust || playerScore < dealerScore -> WinningState(0, 1)
            playerScore > dealerScore -> WinningState(1, 0)
            else -> WinningState(0, 0)
        }
    }
}
