import com.datastax.oss.driver.api.core.CqlSession
import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters._
object MigrationTool {
  def main(args: Array[String]): Unit = {
    val migrationFolderPath = if (args.length > 0) args(0) else "migrations"
    val session = CassandraConnector.createSession()
    try {
      val migrationFiles = getMigrationFiles("migrations")
      migrationFiles.foreach(applyMigration(_, session))
    } finally {
      session.close()
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
