package learningtest.com.google.cloud.monitoring.v3;

import com.google.api.Metric;
import com.google.api.MonitoredResource;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.monitoring.v3.CreateTimeSeriesRequest;
import com.google.monitoring.v3.Point;
import com.google.monitoring.v3.ProjectName;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;
import com.google.monitoring.v3.TypedValue;
import com.google.protobuf.util.Timestamps;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests for {@link MetricServiceClient}.
 *
 * @author Johnny Lim
 */
public class MetricServiceClientTests {

	@Ignore
	@Test
	public void test() throws IOException {
		String projectId = System.getProperty("projectId");
		if (projectId == null) {
			throw new IllegalStateException("Configure '-DprojectId=YOUR_PROJECT_ID'.");
		}

		MetricServiceClient metricServiceClient = MetricServiceClient.create();

		TimeInterval interval = TimeInterval.newBuilder()
				.setEndTime(Timestamps.fromMillis(System.currentTimeMillis()))
				.build();
		TypedValue value = TypedValue.newBuilder()
				.setDoubleValue(123.45)
				.build();
		Point point = Point.newBuilder()
				.setInterval(interval)
				.setValue(value)
				.build();

		List<Point> points = Collections.singletonList(point);

		ProjectName name = ProjectName.of(projectId);

		Map<String, String> metricLabels = new HashMap<>();
		metricLabels.put("store_id", "Pittsburg");
		Metric metric = Metric.newBuilder()
				.setType("custom.googleapis.com/stores/daily_sales")
				.putAllLabels(metricLabels)
				.build();

		Map<String, String> resourceLabels = new HashMap<>();
		resourceLabels.put("project_id", projectId);
		MonitoredResource resource = MonitoredResource.newBuilder()
				.setType("global")
				.putAllLabels(resourceLabels)
				.build();

		TimeSeries timeSeries = TimeSeries.newBuilder()
				.setMetric(metric)
				.setResource(resource)
				.addAllPoints(points)
				.build();

		CreateTimeSeriesRequest request = CreateTimeSeriesRequest.newBuilder()
				.setName(name.toString())
				.addAllTimeSeries(Collections.singletonList(timeSeries))
				.build();

		metricServiceClient.createTimeSeries(request);

		metricServiceClient.close();
	}

}
