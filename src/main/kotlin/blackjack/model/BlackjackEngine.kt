package blackjack.model

import blackjack.model.WinningResult.BLACKJACK
import blackjack.model.WinningResult.WIN
import blackjack.model.WinningResult.LOSE
import blackjack.model.WinningResult.PUSH

class BlackjackEngine(
    val cardDeck: CardDeck = CardDeck()
) {
    fun preparePlayers(eventProvider: EventProvider): Players {
        val names = eventProvider.getNames()
        return Players(names.map { name ->
            Player(
                name,
                makeFirstHand(),
                Amount(eventProvider.getBetAmount(name).toDouble())
            )
        })
    }

    fun prepareDealer(): Dealer = Dealer(hand = makeFirstHand())

    fun drawPlayer(player: Player): Unit = player.draw(cardDeck)

    fun drawDealer(dealer: Dealer): Unit = dealer.drawUntilFinished(cardDeck)

    fun makeFirstHand(): Hand = Hand(List(2) { cardDeck.draw() })

    fun progressPlayersDraw(players: Players, eventListener: EventListener, eventProvider: EventProvider) {
        players.value.forEach { player ->
            eventListener.displayParticipantCards(player.name, player.hand.cards)
        }
        players.value.forEach { player ->
            progressPlayerDrawUntilFinished(player, eventListener, eventProvider)
        }
    }

    fun calculateWinnings(dealer: Dealer, players: Players) {
        players.getPlayers().forEach { player ->
            calculateMoney(dealer, player, dealer.getPlayerResult(player))
        }
    }

    private fun calculateMoney(dealer: Dealer, player: Player, winningResult: WinningResult) {
        when (winningResult) {
            BLACKJACK -> {
                player.settleBlackjack()
                dealer.settleBlackjack(player.betAmount)
            }

            WIN -> {
                player.settleWin()
                dealer.settleLose(player.betAmount)
            }

            PUSH -> player.settlePush()
            LOSE -> {
                player.settleLose()
                dealer.settleWin(player.betAmount)
            }
        }
    }


    private fun progressPlayerDrawUntilFinished(
        player: Player,
        eventListener: EventListener,
        eventProvider: EventProvider,
    ) {
        while (eventProvider.getIsDrawMore(player.name)) {
            drawPlayer(player)
            eventListener.displayParticipantCards(player.name, player.hand.cards)
            if (player.hand.isBust()) return
        }
    }

    fun progressDealerDraw(dealer: Dealer, eventListener: EventListener) {
        drawDealer(dealer)
        eventListener.displayDealerDrawInfo(dealer.name, dealer.getAdditionalDrawCount())
        eventListener.displayParticipantInfo(dealer.name, dealer.hand.cards, dealer.hand.score(), dealer.hand.isBust())
    }

}
