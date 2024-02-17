package casscal.parser
import casscal.config.Config
import scopt.OParser
object CommandLineParser {
  val builder = OParser.builder[Config]
  val parser = {
    import builder._
    OParser.sequence(
      programName("casscal"),
      opt[String]('u', "username")
        .action((x, c) => c.copy(username = Some(x)))
        .text("Username for Cassandra authentication"),
      opt[String]('p', "password")
        .action((x, c) => c.copy(password = Some(x)))
        .text("Password for Cassandra authentication"),
      opt[String]('k', "keyspace")
        .action((x, c) => c.copy(keyspace = Some(x)))
        .text("Keyspace to connect to in Cassandra"),
      opt[Int]('o', "port")
        .action((x, c) => c.copy(port = x))
        .text("Port to connect to Cassandra"),
      opt[String]('l', "migrationFilePath")
        .action((x, c) => c.copy(migrationFilePath = x))
        .text("Location for migrations folder")
    ,
    )
  }
  def parse(args: Array[String]): Option[Config] = {
    OParser.parse(parser, args, Config())
  }
}
