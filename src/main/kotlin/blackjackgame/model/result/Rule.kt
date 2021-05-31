package blackjackgame.model.result

import blackjackgame.model.player.Dealer
import blackjackgame.model.player.Player

fun getResult(dealer: Dealer, player: Player): Result {
    if (dealer.isBurst() && player.isBurst()) {
        return DRAW
    }

    if (!dealer.isBurst() && !player.isBurst()) {
        if (dealer.cards.calculateFinalScore() < player.calculateFinalScore()) {
            return WIN
        }
        if (dealer.cards.calculateFinalScore() > player.calculateFinalScore()) {
            return LOSE
        }
        return DRAW
    }

    if (dealer.isBurst()) {
        return WIN
    }
    return LOSE
}
