package casscal.utils

import casscal.config.Config
import casscal.connector.CassandraConnector
import casscal.parser.CommandLineParser
import casscal.utils.util.{applyMigration, getMigrationFiles}
import com.datastax.oss.driver.api.core.CqlSession
import casscal.version.VersionTracker

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*
object util {
  def getMigrationFiles(folderPath: String): List[String] = {
    val path = Paths.get(folderPath)
    if (Files.exists(path)) {
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

  def applyMigration(filePath: String, session: CqlSession): Unit = {
    println(s"Applying migration: $filePath")
    val cql = new String(Files.readAllBytes(Paths.get(filePath)))
    try {
      session.execute(cql)
      VersionTracker.insertVersion(session, 0)
      println(s"Migration applied successfully: $filePath")
    } catch
      case e: Exception =>
        println(s"Failed to apply migration: $filePath. Error ${e.getMessage}")
  }
}
