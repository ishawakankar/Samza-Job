package org.sample.ep.samza.task;

import org.apache.samza.config.Config;;
import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.SystemStream;
import org.apache.samza.task.*;
import org.sample.ep.samza.service.SamzaService;
import org.apache.samza.system.OutgoingMessageEnvelope;
import org.apache.samza.operators.OutputStream;

public class SamzaTask {

    private SamzaConfig config;
    private SamzaService service;

    public SamzaTask(Config config) {
        init(config);
    }

    public void init(Config config) {

        this.config = new SamzaConfig(config);
        System.out.println("this.config - "+ this.config+", config - "+config);
    }

    public void process(IncomingMessageEnvelope envelope, MessageCollector collector) throws Exception {
        SamzaSource source = new SamzaSource(envelope);
        System.out.println("Source"+source.getMessage());
        System.out.println("Kafka "+source.getSystemStreamPartition());

        collector.send(new OutgoingMessageEnvelope(new SystemStream("kafka", "telemetry.sample1"), "0736", source.getMessage()));
//        service.process(source);
    }

//    public void window() { System.out.println("window() called"); }
}
