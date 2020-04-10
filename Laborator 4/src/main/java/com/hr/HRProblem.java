package com.hr;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class HRProblem {
    public HRProblem() {
        this.residentList = new ArrayList<Resident>();
        this.hospitalSet = new TreeSet<Hospital>();
    }

    private List<Resident> residentList;
    public List<Resident> getResidentList() {
        return this.residentList;
    }
    public void setResidentList(List<Resident> residentList) {
        this.residentList = residentList;
    }

    private Set<Hospital> hospitalSet;
    public Set<Hospital> getHospitalSet() {
        return this.hospitalSet;
    }
    private void setHospitalSet(Set<Hospital> hospitalSet) {
        this.hospitalSet = hospitalSet;
    }

    public boolean addResident(Resident resident) {
        if (this.residentList.contains(resident))
            return false;
        this.residentList.add(resident);
        return true;
    }

    public boolean addHospital(Hospital hospital) {
        if (this.hospitalSet.contains(hospital))
            return false;
        this.hospitalSet.add(hospital);
        return true;
    }
}
