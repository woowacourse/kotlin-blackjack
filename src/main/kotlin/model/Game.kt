package model

import model.card.Deck
import model.participants.Dealer
import model.participants.IdCard
import model.participants.Participant
import model.participants.Player
import model.participants.Players
import model.result.DealerResult
import model.result.PlayersResult
import model.result.Profit

class Game private constructor(private val participants: List<Participant>, val deck: Deck) {
    fun getDealer(): Dealer {
        return participants.first() as Dealer
    }

    fun getPlayers(): Players {
        return Players(participants.filterIsInstance<Player>())
    }

    fun handOut() {
        getAll().forEach { participant ->
            participant.hit(deck.pop())
            participant.hit(deck.pop())
        }
    }

    inline fun playPlayers(
        readDecision: (Player) -> Boolean,
        showHand: (Player) -> Unit,
    ) {
        getPlayers().players.forEach { player ->
            playPlayer(readDecision, showHand, player)
        }
    }

    inline fun playPlayer(
        readDecision: (Player) -> Boolean,
        showHand: (Player) -> Unit,
        player: Player,
    ) {
        player.play(readDecision, showHand, deck::pop)
    }

    inline fun playDealer(showDealerHandOut: (Dealer) -> (Unit)) {
        val dealer = getDealer()

        while (dealer.canHit()) {
            dealer.hit(deck.pop())
            showDealerHandOut(dealer)
        }
    }

    fun getAll(): List<Participant> {
        return participants
    }

    fun getPlayersResult(): PlayersResult {
        return getPlayers().getPlayersResult(getDealer())
    }

    fun getDealerResult(): DealerResult {
        return getDealer().getDealerResult(getPlayers())
    }

    fun getProfitResult(): MutableMap<IdCard, Profit> {
        val result: MutableMap<IdCard, Profit> = mutableMapOf()
        var dealerProfit = Profit()
        result[getDealer().wallet.idCard] = dealerProfit

        getPlayers().players.forEach { player ->
            val playerProfit = player.judgeProfit(getDealer())
            result[player.wallet.idCard] = playerProfit
            dealerProfit += playerProfit
        }

        result[getDealer().wallet.idCard] = -dealerProfit

        return result
    }

    companion object {
        fun create(
            dealer: Dealer,
            players: Players,
            deck: Deck,
        ): Game {
            return Game(listOf(dealer) + players.players, deck)
        }
    }
}
