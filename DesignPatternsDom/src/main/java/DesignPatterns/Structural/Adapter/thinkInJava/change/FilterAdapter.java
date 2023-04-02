package DesignPatterns.Structural.Adapter.thinkInJava.change;

import DesignPatterns.Behavioral.Strategy.thinkInJava.change.Processor;
import DesignPatterns.Structural.Adapter.thinkInJava.base.Filter;
import DesignPatterns.Structural.Adapter.thinkInJava.base.Waveform;

/**
 * 适配器中的代码将接收所拥有的接口，并产生所需要的接口。
 * 此例中，FilterAdapter的构造器接收拥有的接口Filter,然后生成具有所需要的Processor接口的对象。
 * FilterAdapter类中用到了代理。
 */
public class FilterAdapter implements Processor {
    Filter filter;

    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    public String name() {
        return filter.name();
    }

    public Waveform process(Object input) {
        return filter.process((Waveform)input);
    }
}
