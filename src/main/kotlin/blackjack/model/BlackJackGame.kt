package blackjack.model

class BlackJackGame(
    private val participants: Participants,
    private val gameDeck: GameDeck = GameDeck(),
) {
    fun start(printGameSetting: (dealer: Dealer, playerGroup: PlayerGroup) -> Unit) {
        participants.initSetting(gameDeck = gameDeck)
        printGameSetting(participants.dealer, participants.playerGroup)
    }

    fun runPlayersTurn(
        hitOrStay: (nickname: Nickname) -> Boolean,
        showPlayerCards: (player: Player) -> Unit,
    ) {
        participants.playerGroup.drawPlayerCard(
            gameDeck = gameDeck,
            hitOrStay = hitOrStay,
            showPlayerCards = showPlayerCards,
        )
    }

    fun runDealerTurn(printDealerDrawCard: () -> Unit) {
        participants.dealer.drawDealerCard(gameDeck = gameDeck, printDealerDrawCard = printDealerDrawCard)
    }

    fun finish(printEveryCards: (participants: Participants) -> Unit) {
        printEveryCards(participants)
    }
}
