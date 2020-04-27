package DesignPatterns.DesignConcept.IdGenerator.demo1;

import DesignPatterns.DesignConcept.IdGenerator.demo3.IdGenerationFailureException;

public interface IdGenerator {
    String generator() throws IdGenerationFailureException;
}
