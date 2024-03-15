package model.participants

import model.card.Deck
import model.result.DealerResult
import model.result.PlayersResult
import model.result.ResultType

class Game private constructor(private val participants: List<Participant>, private val deck: Deck) {
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

    fun playPlayers(
        readDecision: (Player) -> Boolean,
        showHand: (Player) -> Unit,
    ) {
        getPlayers().players.forEach { player ->
            playPlayer(readDecision, showHand, player)
        }
    }

    private fun playPlayer(
        readDecision: (Player) -> Boolean,
        showHand: (Player) -> Unit,
        player: Player,
    ) {
        while (player.participantState is ParticipantState.Playing && playByDecision(player, readDecision, showHand)) ;
    }

    fun playDealer(showDealerHandOut: (Dealer) -> (Unit)) {
        val dealer = getDealer()

        while (dealer.canHit()) {
            dealer.hit(deck.pop())
            showDealerHandOut(dealer)
        }
    }

    private fun playByDecision(
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
            result[player.participantName] = resultType
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
