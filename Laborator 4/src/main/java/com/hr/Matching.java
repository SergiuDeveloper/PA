package com.hr;

public class Matching {
    public Matching(Resident resident, Hospital hospital) {
        this.resident = resident;
        this.hospital = hospital;

        this.resident.setMatchedHospital(hospital);
        this.hospital.addMathcedResident(resident);
    }

    private Resident resident;
    private Resident getResident() {
        return this.resident;
    }
    private void setResident(Resident resident) {
        this.resident = resident;
    }
    private Hospital hospital;
    private Hospital getHospital() {
        return this.hospital;
    }
    private void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
