package com.hr;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Resident r0 = new Resident(0);
        Resident r1 = new Resident(1);
        Resident r2 = new Resident(2);
        Resident r3 = new Resident(3);

        Hospital h0 = new Hospital(0, 1);
        Hospital h1 = new Hospital(1, 2);
        Hospital h2 = new Hospital(2, 2);

        r0.addHospitalPreference(h0);
        r0.addHospitalPreference(h1);
        r0.addHospitalPreference(h2);

        r1.addHospitalPreference(h0);
        r1.addHospitalPreference(h1);
        r1.addHospitalPreference(h2);

        r2.addHospitalPreference(h0);
        r2.addHospitalPreference(h1);

        r3.addHospitalPreference(h0);
        r3.addHospitalPreference(h2);

        h0.addResidentPreference(r3);
        h0.addResidentPreference(r0);
        h0.addResidentPreference(r1);
        h0.addResidentPreference(r2);

        h1.addResidentPreference(r0);
        h1.addResidentPreference(r2);
        h1.addResidentPreference(r1);

        h2.addResidentPreference(r0);
        h2.addResidentPreference(r1);
        h2.addResidentPreference(r3);

        HRProblem hrProblem = new HRProblem();
        hrProblem.addResident(r0);
        hrProblem.addResident(r1);
        hrProblem.addResident(r2);
        hrProblem.addResident(r3);
        hrProblem.addHospital(h0);
        hrProblem.addHospital(h1);
        hrProblem.addHospital(h2);

        Map<Resident, List<Hospital>> residentPreferencesMap = HRSolution.mapResidentPreferences(hrProblem);
        Map<Hospital, List<Resident>> hospitalPreferencesMap = HRSolution.mapHospitalPreferences(hrProblem);

        residentPreferencesMap.forEach((resident, hospitalList) -> {
            System.out.print("R" + resident.getID() + " is prefered by: ");
            hospitalList.forEach(hospital -> {
                System.out.print("H" + hospital.getID() + " ");
            });
            System.out.println();
        });
        System.out.println();
        hospitalPreferencesMap.forEach((hospital, residentList) -> {
            System.out.print("H" + hospital.getID() + " is prefered by: ");
            residentList.forEach(resident -> {
                System.out.print("R" + resident.getID() + " ");
            });
            System.out.println();
        });

        System.out.print("H0 is acceptable for: ");
        for (Resident resident : hospitalPreferencesMap.get(h0))
            System.out.print("R" + resident.getID() + " ");
        System.out.println();
        System.out.print("H2 is acceptable for: ");
        for (Resident resident : hospitalPreferencesMap.get(h2))
            System.out.print("R" + resident.getID() + " ");
        System.out.println();
    }
}
