package blackjack.domain

class Participants(names: List<String>, participantGenerator: ParticipantGenerator) {
    val dealer: Dealer = participantGenerator.generateDealer()
    val players: Players = Players(names, participantGenerator::generatePlayer)
}
