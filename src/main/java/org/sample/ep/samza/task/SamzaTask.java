package org.sample.ep.samza.task;

import org.apache.samza.config.Config;;
import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.SystemStream;
import org.apache.samza.task.*;
import org.sample.ep.samza.service.SamzaService;
import org.apache.samza.system.OutgoingMessageEnvelope;
import org.apache.samza.task.StreamTask;
import org.apache.samza.task.InitableTask;
import org.apache.samza.operators.OutputStream;

public class SamzaTask implements StreamTask, InitableTask {

    private SamzaConfig config;
    private SamzaService service;
    private static final SystemStream OUTPUT_STREAM = new SystemStream("kafka", "telemetry-sample1");

    public SamzaTask(Config config, TaskContext taskContext) {
        init(config, taskContext);
    }

    public SamzaTask() {

    }

    @Override
    public void init(Config config, TaskContext taskContext)  {
        this.config = new SamzaConfig(config);
        System.out.println("this.config - "+ this.config+", config - "+config);
    }

    @Override
    public void process(IncomingMessageEnvelope incomingMessageEnvelope, MessageCollector messageCollector, TaskCoordinator taskCoordinator)  {
        SamzaSource source = new SamzaSource(incomingMessageEnvelope);

        System.out.println("kafka message"+source.getMessage());

        messageCollector.send(new OutgoingMessageEnvelope(OUTPUT_STREAM, "", source.getMessage()));

    }

}
