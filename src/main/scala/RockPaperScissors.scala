object RockPaperScissors {

  def main(args: Array[String]): Unit = {

    var playing = true
    var humanChoice = ""
    var aiChoice = ""
    var winner = 0

    var humanWins = 0
    var aiWins = 0

    while (playing) {
      aiChoice = aiChoose(humanChoice, aiChoice, winner)
      humanChoice = readInput("Enter choice, or 'quit' to exit: ")
      if (humanChoice == "quit")
        playing = false
      else {
        print("AI chose " + aiChoice + ". ")
        winner = determineWinner(humanChoice, aiChoice)
        winner match {
          case 0 => println("Draw")
          case 1 => println("You won!"); humanWins += 1
          case 2 => println("You lost!"); aiWins += 1
          case _ => println("Invalid entry. Try again")
        }
        println("Score: " + humanWins + "-" + aiWins)
      }
    }
  }

  def aiChoose(lastHumanChoice: String, lastAiChoice: String, lastWinner: Int): String = {
    lastWinner match {
      case 0 => aiChooseWithBias()
      case 1 => aiChooseWithBias(losesTo(lastHumanChoice), 3)
      case 2 => aiChooseWithBias(beats(lastAiChoice), 3)
      case _ => aiChooseWithBias()
    }
  }

  def aiChooseWithBias(biasTowards: String = "", biasVal: Int = 0): String = {
    val rand = scala.util.Random
    println("Bias: " + biasTowards)
    val randInt = rand.nextInt(3 + biasVal)
    if (biasTowards.isEmpty || randInt < 3)
      rpsChoiceFromVal(randInt)
    else
      biasTowards
  }

  def determineWinner(p1Choice: String, p2Choice: String): Int = (rpsValue(p1Choice), rpsValue(p2Choice)) match {
    case (a, _) if a == -1 => -1
    case (_, b) if b == -1 => -1
    case (a, b) if a == b => 0
    case (a, b) => ((a - b) + 3) % 3
    case _ => -1
  }

  def readInput(prompt: String): String = {
    print(prompt)
    scala.io.StdIn.readLine()
  }

  def rpsValue(choice: String): Int = choice.toLowerCase match {
    case "rock" => 1
    case "paper" => 2
    case "scissors" => 3
    case _ => -1
  }

  def rpsChoiceFromVal(choiceValue: Int): String = choiceValue match {
    case 1 => "rock"
    case 2 => "paper"
    case 0 | 3 => "scissors"
    case _ => "error"
  }

  def beats(choice: String): String = {
    rpsChoiceFromVal((rpsValue(choice) + 2) % 3)
  }

  def losesTo(choice: String): String = {
    rpsChoiceFromVal((rpsValue(choice) + 1) % 3)
  }

}
