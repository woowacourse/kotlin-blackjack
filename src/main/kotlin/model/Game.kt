package model

import model.card.Deck
import model.participants.Dealer
import model.participants.Participant
import model.participants.IdCard
import model.participants.ParticipantState
import model.participants.Player
import model.participants.Players
import model.result.DealerResult
import model.result.PlayersResult
import model.result.Profit
import model.result.ResultType

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
        while (player.participantState is ParticipantState.Playing && playByDecision(player, readDecision, showHand)) ;
    }

    inline fun playDealer(showDealerHandOut: (Dealer) -> (Unit)) {
        val dealer = getDealer()

        while (dealer.canHit()) {
            dealer.hit(deck.pop())
            showDealerHandOut(dealer)
        }
    }

    inline fun playByDecision(
        player: Player,
        readDecision: (Player) -> Boolean,
        showHand: (Player) -> Unit,
    ): Boolean {
        val continueToPlay = readDecision(player)
        if (continueToPlay) player.hit(deck.pop())
        showHand(player)
        return continueToPlay
    }

    fun getAll(): List<Participant> {
        return participants
    }

    fun getPlayersResult(): PlayersResult {
        return getPlayers().players.associate {
            it.wallet.idCard to it.judge(getDealer())
        }.run { PlayersResult(this) }
    }

    fun getDealerResult(): DealerResult {
        val result = getPlayers().players
            .map { getDealer().judge(it) }
            .groupingBy { it }
            .eachCount()

        return DealerResult(result)
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
