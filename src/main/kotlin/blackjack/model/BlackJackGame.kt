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

    fun matchResult() {
        participants.players.forEach { player ->
            if ((player.deck.state as GameState.Finished).state == UserState.BUST) {
                player.deck.changeState(
                    userState = (player.deck.state as GameState.Finished).state,
                    result = (player.deck.state as GameState.Finished).result.deepCopy(newDefeat = 1),
                )

                dealer.deck.changeState(
                    userState = (dealer.deck.state as GameState.Finished).state,
                    result = (dealer.deck.state as GameState.Finished).result.deepCopy(newWin = 1),
                )
            }
        }

        participants.players.forEach { player ->
            if ((player.deck.state as GameState.Finished).state != UserState.BUST) {
                if ((dealer.deck.state as GameState.Finished).state == UserState.BUST) {
                    dealer.deck.changeState(
                        userState = (dealer.deck.state as GameState.Finished).state,
                        result = (dealer.deck.state as GameState.Finished).result.deepCopy(newDefeat = 1),
                    )

                    player.deck.changeState(
                        userState = (player.deck.state as GameState.Finished).state,
                        result = (player.deck.state as GameState.Finished).result.deepCopy(newWin = 1),
                    )
                } else if ((dealer.deck.state as GameState.Finished).state == UserState.BLACKJACK) {
                    if ((player.deck.state as GameState.Finished).state == UserState.BLACKJACK) {
                        dealer.deck.changeState(
                            userState = (dealer.deck.state as GameState.Finished).state,
                            result = (dealer.deck.state as GameState.Finished).result.deepCopy(newPush = 1),
                        )

                        player.deck.changeState(
                            userState = (player.deck.state as GameState.Finished).state,
                            result = (player.deck.state as GameState.Finished).result.deepCopy(newPush = 1),
                        )
                    } else {
                        dealer.deck.changeState(
                            userState = (dealer.deck.state as GameState.Finished).state,
                            result = (dealer.deck.state as GameState.Finished).result.deepCopy(newWin = 1),
                        )

                        player.deck.changeState(
                            userState = (player.deck.state as GameState.Finished).state,
                            result = (player.deck.state as GameState.Finished).result.deepCopy(newDefeat = 1),
                        )
                    }
                } else {
                    val dealerPoint = dealer.deck.calculate()
                    val playerPoint = player.deck.calculate()

                    if (dealerPoint == playerPoint) {
                        dealer.deck.changeState(
                            userState = (dealer.deck.state as GameState.Finished).state,
                            result = (dealer.deck.state as GameState.Finished).result.deepCopy(newPush = 1),
                        )

                        player.deck.changeState(
                            userState = (player.deck.state as GameState.Finished).state,
                            result = (player.deck.state as GameState.Finished).result.deepCopy(newPush = 1),
                        )
                    } else if (dealerPoint > playerPoint) {
                        dealer.deck.changeState(
                            userState = (dealer.deck.state as GameState.Finished).state,
                            result = (dealer.deck.state as GameState.Finished).result.deepCopy(newWin = 1),
                        )

                        player.deck.changeState(
                            userState = (player.deck.state as GameState.Finished).state,
                            result = (player.deck.state as GameState.Finished).result.deepCopy(newDefeat = 1),
                        )
                    } else {
                        dealer.deck.changeState(
                            userState = (dealer.deck.state as GameState.Finished).state,
                            result = (dealer.deck.state as GameState.Finished).result.deepCopy(newDefeat = 1),
                        )

                        player.deck.changeState(
                            userState = (player.deck.state as GameState.Finished).state,
                            result = (player.deck.state as GameState.Finished).result.deepCopy(newWin = 1),
                        )
                    }
                }
            }
            player.deck.state
        }
    }
}
