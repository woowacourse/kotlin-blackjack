import controller.GameController
import model.card.Deck
import model.card.DeckRandomGenerationStrategy

fun main() {
    GameController(Deck.create(DeckRandomGenerationStrategy())).start()
}
