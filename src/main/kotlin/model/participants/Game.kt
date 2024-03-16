package model.participants

import model.card.Deck
import model.result.DealerResult
import model.result.PlayersResult
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
        val isYes = readDecision(player)
        if (isYes) player.hit(deck.pop())
        showHand(player)
        return isYes
    }

    fun getAll(): List<Participant> {
        return participants
    }

    fun getPlayersResult(): PlayersResult {
        val result: MutableMap<ParticipantName, ResultType> = mutableMapOf()

        getPlayers().players.forEach { player ->
            val resultType = player.judge(getDealer())
            result[player.wallet.participantName] = resultType
        }
        return PlayersResult(result)
    }

    fun getDealerResult(): DealerResult {
        val result: MutableMap<ResultType, Int> = mutableMapOf()

        getPlayers().players.forEach { player ->
            val resultType = getDealer().judge(player)
            result[resultType] = (result[resultType] ?: 0) + 1
        }
        return DealerResult(result)
    }

    fun getProfitResult(): MutableMap<ParticipantName, Profit> {
        val result: MutableMap<ParticipantName, Profit> = mutableMapOf()
        var dealerProfit = Profit()
        result[getDealer().wallet.participantName] = dealerProfit

        getPlayers().players.forEach { player ->
            val playerProfit = player.judgeProfit(getDealer())
            result[player.wallet.participantName] = playerProfit
            dealerProfit += playerProfit
        }

        result[getDealer().wallet.participantName] = -dealerProfit

        return result
    }

    companion object {
        fun of(
            dealer: Dealer,
            players: Players,
            deck: Deck,
        ): Game {
            return Game(listOf(dealer) + players.players, deck)
        }
    }
}
