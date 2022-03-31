package com.solace.demo.lvq;

import java.util.Objects;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class Conf {
	@Parameter(names = "--help", help = true, description = "Show usage information")
	private boolean help;

	@Parameter(names = "-h", description = "HOST[:PORT], Broker IP address [:port, omit for default]", required = true, order = 0)
	public String host;

	@Parameter(names = "-u", description = "Client username", order = 20)
	public String user = "default";

	@Parameter(names = "-w", description = "Authentication password", order = 21)
	public String pwd = "default";

	@Parameter(names = "-t", description = "Topic to subscribe", order = 30)
	public String topicName;

	@Parameter(names = "-q", description = "Queue to subscribe", order = 31)
	public String queueName;

	public void parse(String[] args) {
		JCommander jc = JCommander.newBuilder().addObject(this).build();
		try {
			jc.parse(args);

			if (help) {
				jc.usage();
				System.exit(0);
			}

			if (Objects.isNull(topicName) && Objects.isNull(queueName)) {
				System.out.println("At least a queue name or a topic name must be specified!");
				jc.usage();
				System.exit(-1);
			}

		} catch (ParameterException e) {
			e.printStackTrace();
			jc.usage();
			System.exit(-1);
		}
	}
}
