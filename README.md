# spark-domain-utils
Collection of Spark Java UDFs for domain processing.

## How to build
You need to have Maven locally installed.  Then simply run the `install` goal:
```bash
mvn clean install
```

and this will generate the spark-domain-utils shaded jar under `target/`.  
Use the shaded jar to avoid dependency conflicts in Spark.
