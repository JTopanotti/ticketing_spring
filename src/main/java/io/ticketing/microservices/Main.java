package io.ticketing.microservices;

import java.net.InetAddress;

import io.ticketing.microservices.services.OrdersServer;
import io.ticketing.microservices.services.RegistrationServer;
import io.ticketing.microservices.services.TicketingServer;

public class Main {

	public static final String NO_VALUE = "NO-VALUE";

	public static void main(String[] args) {

		String serverName = NO_VALUE;
		String port = null;

		// Eureka server assumed to be on localhost
		System.setProperty(RegistrationServer.REGISTRATION_SERVER_HOSTNAME, "localhost");

		// Look for server name and (optional) port property
		// Ignore any -- arguments intended for Spring Boot
		for (String arg : args) {
			if (arg.startsWith("--"))
				continue;

			if (serverName.equals(NO_VALUE))
				serverName = arg;
			else if (port == null)
				port = arg;
			else {
				System.out.println("Unexpected argument: " + arg);
				usage();
				return;
			}
		}

		// No server name supplied, print usage and exit
		if (serverName == NO_VALUE) {
			usage();
			return;
		}

		// Override port, if specified
		if (port != null)
			System.setProperty("server.port", port);

		// Get IP address, useful when running in containers
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println("Running on IP: " + inetAddress.getHostAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Determine which role this application will run as
		if (serverName.equals("registration") || serverName.equals("reg")) {
			RegistrationServer.main(args);
		} else if (serverName.equals("ticketing")) {
			TicketingServer.main(args);
		} else if (serverName.equals("orders")) {
			OrdersServer.main(args);
		} else {
			// Unrecognized server type - print usage and exit
			System.out.println("Unknown server type: " + serverName);
			usage();
		}
	}

	/**
	 * Print application usage information to console.
	 */
	protected static void usage() {
		System.out.println();
		System.out.println("Usage: java -jar ... <server-name> [server-port]");
		System.out.println("     where");
		System.out.println("       server-name is 'reg', 'registration', " + "'ticketing', 'orders'");
		System.out.println("       server-port > 1024");
		System.out.println(
				"     optionally specify --registration.server.hostname=<IP-address> if it is not running on localhost,");
		System.out.println();
	}
}
