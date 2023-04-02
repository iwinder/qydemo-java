package DesignPatterns.DesignConcept.IdGenerator.demo1;

import DesignPatterns.DesignConcept.IdGenerator.demo3.IdGenerationFailureException;

public class LogTraceIdGenerator{
    private IdGenerator idGenerator;
    public LogTraceIdGenerator(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public String generator() throws IdGenerationFailureException {
        return this.idGenerator.generator();
    }
}
