package model.participants

import model.card.Deck
import model.result.DealerResult
import model.result.PlayersResult
import model.result.ResultType

class Participants private constructor(private val participants: List<Participant>) {
    fun getDealer(): Dealer {
        return participants.first() as Dealer
    }

    fun getPlayers(): Players {
        return Players(participants.filterIsInstance<Player>())
    }

    fun handOut(deck: Deck) {
        getAll().forEach { participant ->
            participant.hit(deck.pop())
            participant.hit(deck.pop())
        }
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
        ): Participants {
            return Participants(listOf(dealer) + players.players)
        }
    }
}
