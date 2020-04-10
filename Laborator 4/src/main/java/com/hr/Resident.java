package com.hr;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class Resident {
    public Resident(int id) {
        this.id = id;
        this.hospitalPreferences = Stream.empty();
        this.matchedHospital = null;
    }

    private int id;
    public int getID() {
        return this.id;
    }
    private void setID(int id) {
        this.id = id;
    }

    private Stream<Hospital> hospitalPreferences;
    public Stream<Hospital> getHospitalPreferences() {
        return this.hospitalPreferences;
    }
    private void setHospitalPreferences(Stream<Hospital> hospitalPreferences) {
        this.hospitalPreferences = hospitalPreferences;
    }

    private Hospital matchedHospital;
    public Hospital getMatchedHospital() {
        return this.matchedHospital;
    }
    public void setMatchedHospital(Hospital matchedHospital) {
        this.matchedHospital = matchedHospital;
    }

    public void addHospitalPreference(Hospital hospitalPreference) {
        this.hospitalPreferences = Stream.concat(this.hospitalPreferences, Stream.of(hospitalPreference));
    }
}
