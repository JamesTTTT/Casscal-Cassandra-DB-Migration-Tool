import casscal.connector.CassandraConnector
import casscal.parser.CommandLineParser
import com.datastax.oss.driver.api.core.CqlSession
import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*
object MigrationTool {
  def main(args: Array[String]): Unit = {
    CommandLineParser.cmdParse(args) match {
      case Some(config) =>
        val session = CassandraConnector.createSession(
          contactPoints = config.contactPoints.toList,
          port = config.port,
          keyspace = config.keyspace,
          username = config.username,
          password = config.password
        )
        val migrationFolderPath = config.migrationFilePath

        try {
          val migrationFiles = getMigrationFiles(migrationFolderPath)
          migrationFiles.foreach(applyMigration(_, session))
        } finally {
          session.close()
        }
      case _=>
    }

  }

  private def getMigrationFiles(folderPath: String): List[String] = {
    val path = Paths.get(folderPath)
    if(Files.exists(path)) {
      Files.list(path)
        .iterator()
        .asScala
        .toList
        .map(_.toString)
        .sorted
    } else {
      List.empty
    }
  }

  private def applyMigration(filePath: String, session: CqlSession): Unit = {
    println(s"Applying migration: $filePath")
    val cql = new String(Files.readAllBytes(Paths.get(filePath)))
    try {
      session.execute(cql)
      println(s"Migration applied successfully: $filePath")
    } catch
      case e: Exception =>
        println(s"Failed to apply migration: $filePath. Error ${e.getMessage}")
  }


}
