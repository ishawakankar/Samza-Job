package org.sample.ep.samza.task;

import org.apache.samza.Partition;
import org.apache.samza.config.Config;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;

import org.apache.samza.system.IncomingMessageEnvelope;
import org.apache.samza.system.SystemStreamPartition;
import org.apache.samza.task.MessageCollector;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.sample.ep.samza.fixtures.EventFixture;

public class SamzaTaskTest {

    private static final String SAMPLE_TOPIC = "telemetry.sample";

    private Config configMock;
    private SamzaTask samzaTask;
    private IncomingMessageEnvelope envelopeMock;
    private MessageCollector collector;

    @Before
    public void setUp() {
        configMock = Mockito.mock(Config.class);
        envelopeMock = mock(IncomingMessageEnvelope.class);
        collector = mock(MessageCollector.class);

        stub(configMock.get("output.sample.topic.name", SAMPLE_TOPIC)).toReturn(SAMPLE_TOPIC);
        stub(envelopeMock.getSystemStreamPartition())
                .toReturn(new SystemStreamPartition("kafka", "telemetry.sample", new Partition(0)));
    }

    @Test
    public void shouldCallAllServices() throws Exception {

        samzaTask = new SamzaTask(configMock);
    }

    @Test
    public void shouldReadFromKafkaTopic () throws Exception {
        stub(envelopeMock.getMessage()).toReturn(EventFixture.SampleEvent());

        samzaTask = new SamzaTask(configMock);
        samzaTask.process(envelopeMock, collector);
    }
}
