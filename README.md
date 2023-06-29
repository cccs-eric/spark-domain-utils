# spark-domain-utils
Collection of Spark Java UDFs for domain processing.

## How to build
You need to have Maven locally installed.  Then simply run the `install` goal:
```bash
mvn clean install
```

and this will generate the spark-domain-utils shaded jar under `target/`.  
Use the shaded jar to avoid dependency conflicts in Spark.

## How to use
Once you have a shaded jar, you need to add it to your Spark session.  
This is done by adding the following to your Spark session builder:
```python
.config("spark.jars", "spark-domain-utils-1.0-SNAPSHOT-shaded.jar")
```

Then the jar needs to up added to the Spark context:
```python
spark.sparkContext.addFile("spark-domain-utils-1.0-SNAPSHOT-shaded.jar")
```

And finally, you need to register and name a UDF:
```python
spark.udf.registerJavaFunction("is_subdomain", "cccs.hogwarts.domainutils.IsSubdomainUDF", BooleanType())
```
