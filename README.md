#Samza Job

To run the job:
 - Create kafka topics: "telemetry-sample" and "telemetry-sample1". Push some string messages into "telemetry-sample".
 - In the deploy folder run the following command:
 ```bin/run-job.sh --config-factory=org.apache.samza.config.factories.PropertiesConfigFactory --config-path=config/samzajob.properties```
