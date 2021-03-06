package org.pipservices3.rpc.connect;

import static org.junit.Assert.*;

import org.junit.Test;
import org.pipservices3.commons.config.ConfigParams;
import org.pipservices3.components.connect.ConnectionParams;
import org.pipservices3.rpc.connect.HttpConnectionResolver;
import org.pipservices3.commons.errors.ApplicationException;

public class HttpConnectionResolverTest {

	public HttpConnectionResolverTest() {
	}

	@Test
	public void testConnectionParams() throws ApplicationException {
		HttpConnectionResolver connectionResolver = new HttpConnectionResolver();
		connectionResolver.configure(ConfigParams.fromTuples(
			"connection.protocol", "http",
			"connection.host", "somewhere.com",
			"connection.port", 123
		));

		ConnectionParams connection = connectionResolver.resolve(null);

		assertEquals("http", connection.getProtocol());
		assertEquals("somewhere.com", connection.getHost());
		assertEquals(123, connection.getPort());
		assertEquals("http://somewhere.com:123", connection.getUri());
	}

	@Test
	public void testConnectionUri() throws ApplicationException {
		HttpConnectionResolver connectionResolver = new HttpConnectionResolver();
		connectionResolver.configure(ConfigParams.fromTuples(
			"connection.uri", "https://somewhere.com:123"
		));

		ConnectionParams connection = connectionResolver.resolve(null);

		assertEquals("https", connection.getProtocol());
		assertEquals("somewhere.com", connection.getHost());
		assertEquals(123, connection.getPort());
		assertEquals("https://somewhere.com:123", connection.getUri());
	}
}
