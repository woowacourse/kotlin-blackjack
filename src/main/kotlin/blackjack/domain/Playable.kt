package blackjack.domain

interface Playable {
    val cards: List<Card>
    val score: Score
}
