package casscal.utils

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
      println(s"Migration applied successfully: $filePath")
    } catch
      case e: Exception =>
        println(s"Failed to apply migration: $filePath. Error ${e.getMessage}")
  }
}
