package Structural.Adapter.thinkInJava.change;

import Behavioral.Strategy.thinkInJava.change.Apply;
import Structural.Adapter.thinkInJava.base.BandPass;
import Structural.Adapter.thinkInJava.base.HighPass;
import Structural.Adapter.thinkInJava.base.LowPass;
import Structural.Adapter.thinkInJava.base.Waveform;

public class FilterProcessor {
    public static void main(String[] args) {
        Waveform w = new Waveform();
        Apply.process(new FilterAdapter(new LowPass(1.0)),w);
        Apply.process(new FilterAdapter(new HighPass(2.0)),w);
        Apply.process(new FilterAdapter(new BandPass(3.0,4.0)),w);
    }
}
