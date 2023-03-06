package blackjack

import blackjack.domain.BlackjackResult
import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val deck = Deck.create()
    val players = InputView.getNames().map(::Player)
    val dealer = Dealer()

    onGameStart(deck, players, dealer)
    onGameRun(deck, players, dealer)
    ResultView.printResult(dealer, players, BlackjackResult.of(dealer, players))
}

private fun onGameStart(deck: Deck, players: List<Player>, dealer: Dealer) {
    dealCards(deck, players + dealer)
    ResultView.printSetUp(dealer, players)
}

private fun dealCards(deck: Deck, participants: List<Participant>) {
    repeat(Participant.INIT_CARD_SIZE) {
        participants.forEach { it.receive(deck.draw()) }
    }
}

private fun onGameRun(deck: Deck, players: List<Player>, dealer: Dealer) {
    decideHitOrStand(deck, players)
    checkDealerHitOrStand(deck, dealer)
}

private fun decideHitOrStand(deck: Deck, players: List<Player>) {
    players.forEach { decideHitOrStand(deck, it) }
}

private fun decideHitOrStand(deck: Deck, player: Player) {
    while (player.canHit() && InputView.doesPlayerWantHit(player.name)) {
        player.receive(deck.draw())
        ResultView.printCards(player)
    }
}

private fun checkDealerHitOrStand(deck: Deck, dealer: Dealer) {
    if (dealer.shouldHit()) {
        dealer.receive(deck.draw())
        ResultView.printDealerHitMessage(dealer.name)
    }
}
