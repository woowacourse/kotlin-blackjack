package blackjack.controller

import blackjack.domain.Card
import blackjack.domain.Deck
import blackjack.domain.Rank
import blackjack.domain.Suit
import blackjack.domain.update.NewBetting
import blackjack.domain.update.participant.NewDealer
import blackjack.domain.update.participant.NewPlayer
import blackjack.domain.update.participant.Participant
import blackjack.domain.update.state.NewParticipantState
import blackjack.view.model.DealerSummary
import blackjack.view.model.PlayerConfig
import blackjack.view.model.PlayerResult
import blackjack.view.model.PlayerSummary

fun List<PlayerConfig>.toNewPlayers(deck: Deck): List<NewPlayer> = map { playerConfig -> playerConfig.toNewPlayer(deck) }

private fun PlayerConfig.toNewPlayer(deck: Deck): NewPlayer = NewPlayer(name, NewBetting(bettingAmount), deck)

val List<NewPlayer>.names: List<String> get() = map { player -> player.name }

private val Rank.prettyString: String
    get() =
        when (this) {
            Rank.AceRank -> "A"
            Rank.NumberRank.TWO -> "2"
            Rank.NumberRank.THREE -> "3"
            Rank.NumberRank.FOUR -> "4"
            Rank.NumberRank.FIVE -> "5"
            Rank.NumberRank.SIX -> "6"
            Rank.NumberRank.SEVEN -> "7"
            Rank.NumberRank.EIGHT -> "8"
            Rank.NumberRank.NINE -> "9"
            Rank.NumberRank.TEN -> "10"
            Rank.FaceRank.JACK -> "J"
            Rank.FaceRank.QUEEN -> "Q"
            Rank.FaceRank.KING -> "K"
        }

private val Suit.prettyString: String
    get() =
        when (this) {
            Suit.SPADE -> "♠"
            Suit.HEART -> "♥"
            Suit.DIAMOND -> "♦"
            Suit.CLOVER -> "♣"
        }

val NewDealer.cardsPrettyString: List<String> get() = cards.prettyString

val List<Participant>.cardsPrettyStrings: List<List<String>>
    get() = map { participant -> participant.cards.prettyString }

val List<Card>.prettyString: List<String>
    get() = map { card: Card -> card.prettyString }

private val Card.prettyString: String
    get() = rank.prettyString + suit.prettyString

val Participant.summary: DealerSummary
    get() =
        DealerSummary(
            cards.prettyString,
            score.value,
        )

private val NewPlayer.summary: PlayerSummary
    get() = PlayerSummary(name, cards.prettyString, score.value)

val List<NewPlayer>.summaries: List<PlayerSummary>
    get() = map { player -> player.summary }

fun List<NewPlayer>.toResults(dealerState: NewParticipantState): List<PlayerResult> =
    map { player -> PlayerResult(player.name, player.calculateProfit(dealerState).toInt()) }
