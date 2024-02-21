package casscal.cli

import casscal.config.Config
import casscal.connector.CassandraConnector
import casscal.parser.CommandLineParser
import casscal.utils.util.{applyMigration, getMigrationFiles}
import com.datastax.oss.driver.api.core.CqlSession

import java.nio.file.{Files, Paths}
import scala.jdk.CollectionConverters.*

object MigrationTool {
  def main(args: Array[String]): Unit = {
    CommandLineParser.parse(args) match {
      case Some(config) =>
        val session = CassandraConnector.createSession(
          contactPoints = config.contactPoints.toList,
          port = config.port,
          keyspace = config.keyspace,
          username = config.username,
          password = config.password,
          localDatacenter = config.localDatacenter
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




}
