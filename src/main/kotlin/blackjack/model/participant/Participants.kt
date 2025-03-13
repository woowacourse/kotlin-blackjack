package blackjack.model.participant

class Participants(
    val dealer: Dealer,
    val players: Players,
) {
    init {
        require(players.value.find { player -> player.name == dealer.name } == null) {
            "[ERROR] 플레이어와 딜러의 이름은 중복될 수 없습니다."
        }
    }
}
