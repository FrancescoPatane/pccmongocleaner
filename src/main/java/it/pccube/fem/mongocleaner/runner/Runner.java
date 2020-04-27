package it.pccube.fem.mongocleaner.runner;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.process.FileManager;
import it.pccube.fem.mongocleaner.process.Processor;

@Component
public class Runner implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(Runner.class);

	
	@Autowired
	private Processor processor;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public void run(String... args) throws Exception {
		logger.info("###START " + LocalDateTime.now() + "###");
		processor.setInputFilePath(args[0]);
		fileManager.setOutcomeFilePath(args[1]);
		processor.processDiscardedFile();
		logger.info("###END###");
	}

}
