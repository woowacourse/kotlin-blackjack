package blackjack.view

import blackjack.domain.model.Rank
import blackjack.domain.model.Suit
import blackjack.domain.model.playing.PlayingParticipant
import blackjack.domain.model.playing.PlayingParticipants
import blackjack.domain.model.playing.PlayingPlayer
import blackjack.domain.model.profit.ProfitParticipant

class OutputView {
    fun printInitialDeals(playingParticipants: PlayingParticipants) {
        println(
            MESSAGE_INITIAL_HAND_DISTRIBUTED.format(
                playingParticipants.dealer.name,
                playingParticipants.players.map(PlayingPlayer::name).joinToString(PLAYER_CARDS_DELIMITER),
                START_CARD_COUNT,
            ),
        )
    }

    fun printParticipantsStatus(participants: PlayingParticipants) {
        participants.participants.forEach { participant ->
            println(renderParticipantsInitStatus(participant))
        }
    }

    private fun renderParticipantsInitStatus(playingParticipant: PlayingParticipant): String {
        return playingParticipant.name + PLAYER_NAME_STATUS_DELIMITER +
            playingParticipant.showStartCards()
                .joinToString { convertKoreanRank(it.rank) + convertKoreanSuit(it.suit) }
    }

    fun printPlayerStatus(player: PlayingParticipant) {
        println(renderParticipantsStatus(player))
    }

    fun printParticipantsResult(playingParticipants: PlayingParticipants) {
        playingParticipants.participants.forEach { participant ->
            println(renderParticipantsStatus(participant) + PLAYER_RESULT_DELIMITER + participant.handsState.score())
        }
    }

    private fun renderParticipantsStatus(playingParticipant: PlayingParticipant): String {
        return playingParticipant.name + PLAYER_NAME_STATUS_DELIMITER +
            playingParticipant.showCards()
                .joinToString { convertKoreanRank(it.rank) + convertKoreanSuit(it.suit) }
    }

    fun printDealerHitsState() {
        println(MESSAGE_DEALER_HITS_STATE)
    }

    fun printResultsHeader() {
        println(MESSAGE_RESULTS_HEADER)
    }

    fun printDealerProfit(profitDealer: ProfitParticipant) {
        print(profitDealer.name + NAME_RESULT_DELIMITER + profitDealer.profit.value)
        println()
    }

    fun printPlayersProfit(profitPlayers: List<ProfitParticipant>) {
        profitPlayers.forEach { profitParticipant ->
            println(profitParticipant.name + NAME_RESULT_DELIMITER + profitParticipant.profit.value)
        }
    }

    fun printErrorMessage(message: String) {
        println(message)
    }

    private fun convertKoreanSuit(suit: Suit): String {
        return when (suit) {
            Suit.HEART -> "하트"
            Suit.SPADE -> "스페이드"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }

    private fun convertKoreanRank(rank: Rank): String {
        return when (rank) {
            Rank.ACE -> "A"
            Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE, Rank.SIX, Rank.SEVEN, Rank.EIGHT, Rank.NINE, Rank.TEN -> rank.score.toString()
            Rank.JACK, Rank.QUEEN, Rank.KING -> Rank.TEN.score.toString()
        }
    }

    companion object {
        private const val START_CARD_COUNT = 2
        private const val MESSAGE_INITIAL_HAND_DISTRIBUTED = "%s와(과) %s에게 %s장의 카드를 나누었습니다."
        private const val MESSAGE_DEALER_HITS_STATE = "딜러는 16이하라 한장의 카드를 더 받았습니다."
        private const val MESSAGE_RESULTS_HEADER = "## 최종 승패"
        private const val PLAYER_CARDS_DELIMITER = ", "
        private const val PLAYER_NAME_STATUS_DELIMITER = " 카드: "
        private const val PLAYER_RESULT_DELIMITER = " - 결과: "
        private const val NAME_RESULT_DELIMITER = ": "
    }
}
