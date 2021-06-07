package blackjackgame.model.result

import blackjackgame.model.player.Dealer
import blackjackgame.model.player.Player


fun getPlayerResult(dealer: Dealer, player: Player): Result {
    if (!dealer.isBurst() && !player.isBurst()) {
        if (dealer.cards.calculateFinalScore() < player.calculateFinalScore()) {
            player.earnMoney(WIN)
            return WIN
        }
        if (dealer.cards.calculateFinalScore() > player.calculateFinalScore()) {
            player.earnMoney(LOSE)
            return LOSE
        }
        player.earnMoney(DRAW)
        return DRAW
    }

    if (dealer.isBurst()) {
        player.earnMoney(WIN)
        return WIN
    }
    player.earnMoney(LOSE)
    return LOSE
}
