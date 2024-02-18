package casscal.connector

import com.datastax.oss.driver.api.core.CqlSession
import com.datastax.oss.driver.internal.core.ContactPoints
import sun.security.util.Password
import scala.jdk.CollectionConverters._
import java.net.InetSocketAddress
object CassandraConnector {
  def createSession(contactPoints: List[String],
                    port: Int,
                    keyspace: Option[String] = None,
                    username: Option[String] = None,
                    password: Option[String] = None,
                    localDatacenter: String
                   ): CqlSession = {

    val builder = CqlSession.builder()
      .addContactPoints(contactPoints.map(InetSocketAddress.createUnresolved(_,port)).asJava)
      .withLocalDatacenter(localDatacenter)

    keyspace.foreach(builder.withKeyspace)

    (username, password) match {
      case (Some(u), Some(p)) => builder.withAuthCredentials(u, p)
      case _ =>
    }

    builder.build()
  }
}
