package org.sample.ep.samza.task;

import org.apache.samza.config.Config;

public class SamzaConfig {

    private final String JOB_NAME = "SamzaJob";

    private String sampleTopic;

    public SamzaConfig(Config config) {
        sampleTopic = config.get("output.sample.topic.name", "telemetry.sample");
    }

    public String getSampleTopic() { return sampleTopic; }

    public void setSampleTopic() { this.sampleTopic = sampleTopic; }
}
