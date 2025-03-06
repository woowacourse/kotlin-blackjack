package blackjack.model

class Players private constructor(
    val players: List<Player>,
) {
    constructor(
        players: List<String>,
        cardDeck: CardDeck,
    ) : this(players.map { name -> Player(name, cardDeck) })

    init {
        require(players.size in 1..7) {
            "[ERROR] 플레이어 수는 1명 이상부터 7명 이하만 가능합니다. 입력값: ${players.size}"
        }
        require(players.distinct().size == players.size) {
            "[ERROR] 플레이어 이름은 중복될 수 없습니다. 입력값: ${players.joinToString { ", " }}"
        }
    }

    fun results(dealerScore: Int): Map<String, WinningResult> =
        players.associate { player ->
            player.name to WinningResult.from(player.hand.score(), dealerScore)
        }

    fun scores(): List<Int> = players.map { player -> player.hand.score() }
}
