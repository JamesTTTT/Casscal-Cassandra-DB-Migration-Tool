![casscalLogo](https://github.com/JamesTTTT/Casscal-Cassandra-DB-Migration-Tool/assets/66074363/164a0ebb-612c-4f49-8523-027be858e79d)
# Casscal - (Work in progress)
### A Cassandra database migration tool
This is a tool i have created for myself to easily simplify Casssandra database migration

The tool is entirely written in Scala 3.4

| Flag (Short) | Flag (Long)           | Description                                 | Input Type |
|--------------|-----------------------|---------------------------------------------|------------|
| `-u`         | `--username`          | Username for Cassandra authentication       | String     |
| `-p`         | `--password`          | Password for Cassandra authentication       | String     |
| `-k`         | `--keyspace`          | Keyspace to connect to in Cassandra         | String     |
| `-o`         | `--port`              | Port to connect to Cassandra                | Int        |
| None         | `--datacenter`        | Local datacenter for Cassandra connection   | String     |
| `-l`         | `--migrationFilePath` | Location for migrations folder              | String     |

### Usage Example

Run the `casscal` application with the following command, replacing each argument with your specific values:

```shell
casscal --username myUsername --password myPassword --keyspace myKeyspace --port 9042 --datacenter myDatacenter --migrationFilePath /path/to/migrations
