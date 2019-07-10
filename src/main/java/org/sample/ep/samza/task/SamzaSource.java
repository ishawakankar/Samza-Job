package org.sample.ep.samza.task;

import java.util.Map;

import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.SystemStreamPartition;

import com.google.gson.Gson;

public class SamzaSource {

    private IncomingMessageEnvelope envelope;

    public SamzaSource(IncomingMessageEnvelope envelope) {
        this.envelope = envelope;
    }

    public String getMessage() {
        return envelope.getMessage().toString();
    }
    public SystemStreamPartition getSystemStreamPartition() { return envelope.getSystemStreamPartition();}
    public String getOffset() { return envelope.getOffset();}
}
