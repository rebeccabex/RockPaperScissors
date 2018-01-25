package rps

object RockPaperScissors {
  val human = new Human(0)
  val ai = new AI(1)

  def main(args: Array[String]): Unit = {

    var playing = true
    var humanChoice = ""
    var aiChoice = ""

    while (playing) {
      aiChoice = ai.choose()
      humanChoice = human.choose()
      humanChoice match {
        case "quit" => playing = false
        case "rock" | "paper" | "scissors" => playRound(humanChoice, aiChoice)
        case _ => println("Invalid entry. Try again")
      }
    }
  }

  def playRound(humanChoice: String, aiChoice: String): Unit = {
    print("AI chose " + aiChoice + ". ")
    val winner = determineWinner(humanChoice, aiChoice)
    winner match {
      case 0 => println("Draw"); human.drew(); ai.drew()
      case 1 => println("You won!"); human.won(); ai.lost()
      case 2 => println("You lost!"); ai.won(); human.lost()
      case _ => println("Invalid entry. Try again")
    }
    println("Score: " + human.wins + "-" + ai.wins)
  }

  def determineWinner(p1Choice: String, p2Choice: String): Int = (RPS.rpsValue(p1Choice), RPS.rpsValue(p2Choice)) match {
    case (a, _) if a == -1 => -1
    case (_, b) if b == -1 => -1
    case (a, b) if a == b => 0
    case (a, b) => ((a - b) + 3) % 3
    case _ => -1
  }

}
