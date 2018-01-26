package rps

object RockPaperScissors {

  def main(args: Array[String]): Unit = {

    var playing = true
    var inGame = true
    val players = new Array[Player](2)
    var noOfRounds = 0
    var roundsPlayed = 0

    while (inGame) {
      getInput("What game do you want to play? 0 - quit, 1 - Human v AI, 2 - AI v AI: ").toInt match {
        case 0 => playing = false; inGame = false
        case 1 => players(0) = new Human()
        case 2 => players(0) = new AI(); noOfRounds = getInput("How many rounds? ").toInt
        case _ => println("Invalid entry");
      }
      players(1) = new AI()

      while (playing) {

        val choices = new Array[String](2)
        for (i <- 0 to 1)
          choices(i) = players(i).choose()

        if (players(0).isInstanceOf[Human])
          choices(0) match {
            case "quit" => playing = false
            case "rock" | "paper" | "scissors" => playRound(choices(0), choices(1))
            case _ => println("Invalid entry. Try again")
          }
        else if (roundsPlayed < noOfRounds) {
          playRound(choices(0), choices(1))
          roundsPlayed += 1
        }
        else
          playing = false
      }

      def playRound(p1Choice: String, p2Choice: String): Unit = {
        print(p1Choice + " v " + p2Choice + ": ")
        val winner = determineWinner(p1Choice, p2Choice)
        winner match {
          case 0 => println("Draw"); players(0).drew(); players(1).drew()
          case 1 => println("Player 1 won!"); players(0).won(); players(1).lost()
          case 2 => println("Player 2 won!"); players(1).won(); players(0).lost()
          case _ => println("Invalid entry. Try again")
        }
        println("Score: " + players(0).wins + "-" + players(1).wins)
      }

      def determineWinner(p1Choice: String, p2Choice: String): Int = (RPS.rpsValue(p1Choice), RPS.rpsValue(p2Choice)) match {
        case (a, _) if a == -1 => -1
        case (_, b) if b == -1 => -1
        case (a, b) if a == b => 0
        case (a, b) => ((a - b) + 3) % 3
        case _ => -1
      }
    }
  }

  def getInput(prompt: String): String = {
    print(prompt)
    scala.io.StdIn.readLine()
  }

}
