package DesignPatterns.DesignConcept.Aggregator.v5;

import DesignPatterns.DesignConcept.Aggregator.v2.EmailSender;
import DesignPatterns.DesignConcept.Aggregator.v2.RequestStat;
import DesignPatterns.DesignConcept.Aggregator.v3.StatViewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailViewer implements StatViewer {

    private EmailSender emailSender;
    private List<String> toAddress = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender();
    }
    public EmailViewer(EmailSender emailSender) {
        this.emailSender = emailSender;

    }

    public EmailViewer(List<String> emailToAddresses) {
        this();
        toAddress = emailToAddresses;
    }

    public void addToAddress(String address) {
        toAddress.add(address);
    }
    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMillis) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
