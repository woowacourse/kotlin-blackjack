package blackjack.model

class BlackJackGame(
    val dealer: Dealer,
    val participants: Participants,
) {
    fun drawTwoCards(gameDeck: GameDeck) {
        repeat(2) {
            dealer.takeCard(gameDeck.drawCard())
            participants.players.forEach { player ->
                player.takeCard(gameDeck.drawCard())
            }
        }
    }

    fun drawPlayerCard(
        gameDeck: GameDeck,
        input: (player: Player) -> Boolean,
        output: (player: Player) -> Unit,
    ) {
        participants.players.forEach { player ->
            while (player.deck.state == GameState.Running) {
                // 사용자 입력
                if (input(player)) {
                    player.takeCard(gameDeck.drawCard())
                    output(player)
                } else {
                    player.deck.changeState(UserState.STAND)
                }
            }
        }
    }

    fun drawDealerCard(
        gameDeck: GameDeck,
        output: () -> Unit,
    ) {
        while (dealer.deck.state == GameState.Running) {
            // 사용자 입력
            if (dealer.deck.calculate() <= 16) {
                output()
                dealer.takeCard(gameDeck.drawCard())
            } else {
                dealer.deck.changeState(UserState.STAND)
            }
        }
    }
}
