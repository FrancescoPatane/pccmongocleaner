package it.pccube.fem.mongocleaner.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import it.pccube.fem.mongocleaner.process.FileManager;
import it.pccube.fem.mongocleaner.process.Processor;

@Component
public class Runner implements CommandLineRunner{
	
	@Autowired
	private Processor processor;
	
	@Autowired
	private FileManager fileManager;

	@Override
	public void run(String... args) throws Exception {
		fileManager.createFailureFileForLaunch();
		processor.processDiscardedFile();
		
	}

}
