package service;

import model.ExecutionRecord;

import java.util.ArrayList;
import java.util.List;

public class HistoryService {

    private final List<ExecutionRecord> records =
            new ArrayList<>();

    public void addRecord(
            ExecutionRecord record) {

        records.add(record);
    }

    public void showHistory() {

        if(records.isEmpty()) {

            System.out.println("No execution history available.");
            return;
        }

        System.out.println("\n===== EXECUTION HISTORY =====");

        records.forEach(System.out::println);
    }
}