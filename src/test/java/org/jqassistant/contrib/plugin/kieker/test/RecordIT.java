package org.jqassistant.contrib.plugin.kieker.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;

import com.buschmais.jqassistant.core.scanner.api.DefaultScope;
import com.buschmais.jqassistant.plugin.common.test.AbstractPluginIT;

public class RecordIT extends AbstractPluginIT {

	@Test
	public void testRecord() {
		final String TEST_DIRECTORY_PATH = "src/test/resources/";
		File directory = new File(TEST_DIRECTORY_PATH);
		store.beginTransaction();
		getScanner().scan(directory, TEST_DIRECTORY_PATH, DefaultScope.NONE);
		TestResult testResult = query("MATCH (:Record)-[:CONTAINS]->(t:Trace) RETURN t");
		// one record with two traces
		assertThat(testResult.getColumn("t").size(), equalTo(2));
		store.commitTransaction();
	}
}
