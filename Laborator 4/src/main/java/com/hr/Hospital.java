package com.hr;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Hospital implements Comparable<Hospital> {
    public Hospital(int id, int capacity) {
        this.id = id;
        this.matchedResidents = new ArrayList<Resident>();
        this.residentPreferences = Stream.empty();

        this.capacity = capacity;
    }

    private int id;
    public int getID() {
        return this.id;
    }
    private void setID(int id) {
        this.id = id;
    }

    private Stream<Resident> residentPreferences;
    public Stream<Resident> getResidentPreferences() {
        return this.residentPreferences;
    }
    private void setResidentPreferences(Stream<Resident> residentPreferences) {
        this.residentPreferences = residentPreferences;
    }

    private List<Resident> matchedResidents;
    private List<Resident> getMatchedResidents() {
        return this.matchedResidents;
    }
    private void setMatchedResidents(List<Resident> matchedResidents) {
        this.matchedResidents = matchedResidents;
    }

    private int capacity;
    private int getCapacity() {
        return this.capacity;
    }
    private void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean addMathcedResident(Resident matchedResident) {
        if (this.matchedResidents.contains(matchedResident))
            return false;
        this.matchedResidents.add(matchedResident);
        return true;
    }

    public void addResidentPreference(Resident residentPreference) {
        this.residentPreferences = Stream.concat(this.residentPreferences, Stream.of(residentPreference));
    }

    @Override
    public int compareTo(Hospital o) {
        int neededResidents1 = this.capacity - this.matchedResidents.size();
        int neededResidents2 = o.capacity - o.matchedResidents.size();

        return neededResidents2 - neededResidents1;
    }
}
