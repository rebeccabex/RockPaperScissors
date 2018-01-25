object RockPaperScissors {

  def main(args: Array[String]): Unit = {
    var playing = true
    while (playing) {
      val input = readInput("Enter choice, or 'quit' to exit: ")
      if (input == "quit")
        playing = false
      else {
        determineWinner(input, aiGuess()) match {
          case 0 => println("Draw")
          case 1 => println("You won!")
          case 2 => println("You lost")
          case _ => println("Invalid entry. Try again")
        }
      }
    }
  }

  def aiGuess(): String = {
    val rand = scala.util.Random
    rand.nextInt(3) match {
      case 0 => "rock"
      case 1 => "paper"
      case 2 => "scissors"
    }
  }

  def determineWinner(p1Choice: String, p2Choice: String): Int = (rpsValue(p1Choice), rpsValue(p2Choice)) match {
    case (a, _) if a == -1 => -1
    case (_, b) if b == -1 => -1
    case (a, b) if a == b => 0
    case (a, b) => ((a - b) + 3) % 3
    case _ => -1
  }

  def rpsValue(choice: String): Int = choice.toLowerCase match {
    case "rock" => 1
    case "paper" => 2
    case "scissors" => 3
    case _ => -1
  }

  def readInput(prompt: String): String = {
    print(prompt)
    scala.io.StdIn.readLine()
  }

}
