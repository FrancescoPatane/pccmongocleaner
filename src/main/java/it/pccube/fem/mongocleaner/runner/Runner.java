package it.pccube.fem.mongocleaner.runner;

import java.util.Arrays;

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
		System.out.println(Arrays.asList(args));
//		fileManager.createFailureFileForLaunch();
		processor.setInputFilePath(args[0]);
		fileManager.setFailuresFilePath(args[1]);
		processor.processDiscardedFile(args[0]);
		
	}

}
