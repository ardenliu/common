package com.github.ardenliu.common.picocli;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;

import picocli.CommandLine;
import picocli.CommandLine.IFactory;

public abstract class AbstractApplicationRunner<T> implements CommandLineRunner, ExitCodeGenerator{

    private final T command;

    private final IFactory factory; // auto-configured to inject PicocliSpringFactory

    private int exitCode;
    
    public AbstractApplicationRunner(T command, IFactory factory) {
        this.command = command;
        this.factory = factory;
    }
    
    @Override
    public int getExitCode() {
        return exitCode;
    }

    @Override
    public void run(String... args) throws Exception {
        exitCode = new CommandLine(command, factory).execute(args);
    }
}