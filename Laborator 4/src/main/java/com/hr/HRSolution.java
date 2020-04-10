package com.hr;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class HRSolution {
    public HRSolution(HRProblem hrProblem) {
        this.hrProblem = hrProblem;
        this.matchings = new ArrayList<Matching>();
    }

    private HRProblem hrProblem;
    private HRProblem getHRProblem() {
        return this.hrProblem;
    }
    private void setHrProblem(HRProblem hrProblem) {
        this.hrProblem = hrProblem;
    }

    private List<Matching> matchings;
    public List<Matching> getMatchings() {
        return this.matchings;
    }
    private void setMatchings(List<Matching> matchings) {
        this.matchings = matchings;
    }

    public static HRSolution computeSolution(HRProblem hrProblem) {
        List<Resident> residentList = hrProblem.getResidentList();
        residentList.sort(new ResidentsComparator());
        hrProblem.setResidentList(residentList);

        HRSolution hrSolution = new HRSolution(hrProblem);

        //to-add

        return hrSolution;
    }

    public static Map<Resident, List<Hospital>> mapResidentPreferences(HRProblem hrProblem) {
        Map<Resident, List<Hospital>> residentPreferencesMap = new HashMap<Resident, List<Hospital>>();
        Supplier<Stream<Resident>> residentPreferencesSupplier;
        for (Hospital hospital : hrProblem.getHospitalSet()) {
            residentPreferencesSupplier = () -> hospital.getResidentPreferences();
            residentPreferencesSupplier.get().forEach(resident -> {
                List<Hospital> currentHospitalList = new ArrayList<Hospital>(residentPreferencesMap.getOrDefault(resident, new ArrayList<Hospital>()));
                currentHospitalList.add(hospital);
                residentPreferencesMap.put(resident, currentHospitalList);
            });
        }
        return residentPreferencesMap;
    }

    public static Map<Hospital, List<Resident>> mapHospitalPreferences(HRProblem hrProblem) {
        Map<Hospital, List<Resident>> hospitalPreferencesMap = new Hashtable<Hospital, List<Resident>>();
        for (Resident resident : hrProblem.getResidentList())
            resident.getHospitalPreferences().forEach(hospital -> {
                List<Resident> currentResidentList = new ArrayList<Resident>(hospitalPreferencesMap.getOrDefault(hospital, new ArrayList<Resident>()));
                currentResidentList.add(resident);
                hospitalPreferencesMap.put(hospital, currentResidentList);
            });
        return hospitalPreferencesMap;
    }

    public static List<Resident> getResidentsHavingTopHospital(HRProblem hrProblem, Hospital hospital) {
        List<Resident> residentsHavingTopHospital = new ArrayList<Resident>();

        Supplier<Stream<Hospital>> hospitalsPreferencesSupplier;
        for (Resident resident : hrProblem.getResidentList()) {
            hospitalsPreferencesSupplier = () -> resident.getHospitalPreferences();
            if (hospitalsPreferencesSupplier.get().findFirst().get() == hospital)
                residentsHavingTopHospital.add(resident);
        }

        return residentsHavingTopHospital;
    }

    public static List<Hospital> getHospitalsHavingTopResident(HRProblem hrProblem, Resident resident) {
        List<Hospital> hospitalsHavingTopResident = new ArrayList<Hospital>();

        Supplier<Stream<Resident>> residentsPreferencesSupplier;
        for (Hospital hospital : hrProblem.getHospitalSet()) {
            residentsPreferencesSupplier = () -> hospital.getResidentPreferences();
            if (residentsPreferencesSupplier.get().iterator().next() == resident)
                hospitalsHavingTopResident.add(hospital);
        }

        return hospitalsHavingTopResident;
    }

    public boolean addMatching(Matching matching) {
        if (this.matchings.contains(matching))
            return false;
        this.matchings.add(matching);
        return true;
    }

    private static class ResidentsComparator implements Comparator<Resident> {
        @Override
        public int compare(Resident t1, Resident t2) {
            if (t1.getMatchedHospital() == null) {
                if (t2.getMatchedHospital() == null)
                    return -1;
                return 0;
            }
            return 1;
        }
    }
}
