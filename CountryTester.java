// This program tests the Country class for MAMAN 14 
// By: Rimon Sade
// Date: 6/14/2023

public class CountryTester {
    public static void main(String[] args) {
        Boolean test1, test2, test3, test4, test5, test6, test7;
        // Create a new Country object
        Country country = new Country("TestCountry");

        // 0
        // Adding some cities to the country
        System.out.println("Adding cities...");
        country.addCity("City1", 1, 1, 2000, 1, 1, 2, 2, 1000, 2);
        country.addCity("City2", 2, 2, 2001, 3, 3, 4, 4, 2000, 3);
        country.addCity("City3", 3, 3, 2002, 5, 5, 6, 6, 3000, 4);
        country.addCity("City4", 4, 4, 2003, 7, 7, 8, 8, 4000, 5);

        System.out.println("Starting tests...");
        System.out.println();

        // 1
        // getNumOfResidents
        // Calculate and print total number of residents in the country
        int residents = country.getNumOfResidents();
        System.out.println("getNumOfResidents:");
        if (residents == 10000) {
            System.out.println("PASS: " + residents + " (Expected: 10000)");
            test1 = true;
        } else {
            System.out.println("FAIL: " + residents + " (Expected: 10000)");
            test1 = false;
        }
        System.out.println();

        // 2
        // longestDistance
        // Calculate and print longest distance between two cities in the country
        double longest = country.longestDistance();
        System.out.println("longest distance:");
        if (longest == 8.48528137423857) {
            System.out.println("PASS: " + longest + " (Expected: 8.48528137423857)");
            test2 = true;
        } else {
            System.out.println("FAIL: " + longest + " (Expected: 8.48528137423857)");
            test2 = false;
        }
        System.out.println();

        // 3
        // Calculate and print number of cities north of a specific city
        int northCities = country.numCitiesNorthOf("City1");
        System.out.println("Number of cities north of City1:");
        if (northCities == 3) {
            System.out.println("PASS: " + northCities + " (Expected: 3)");
            test3 = true;
        } else {
            System.out.println("FAIL: " + northCities + " (Expected: 3)");
            test3 = false;
        }
        System.out.println();

        // 4
        // Print the southernmost city in the country
        City southCity = country.southernmostCity();
        System.out.println("Southernmost city:");
        if (southCity.getCityName().equals("City1")) {
            System.out.println("PASS: " + southCity.getCityName() + " (Expected: City1)");
            test4 = true;
        } else {
            System.out.println("FAIL: " + southCity.getCityName() + " (Expected: City1)");
            test4 = false;
        }
        System.out.println();

        // 5
        // Check if any cities were established before or after certain dates
        Date early = new Date(1, 1, 1999);
        Date late = new Date(1, 1, 2005);
        boolean beforeAfter = country.wereCitiesEstablishedBeforeOrAfter(early, late);
        System.out.println("Were cities established before 1/1/1999 or after 1/1/2005?");
        if (!beforeAfter) {
            System.out.println("PASS: " + beforeAfter + " (Expected: false)");
            test5 = true;
        } else {
            System.out.println("FAIL: " + beforeAfter + " (Expected: false)");
            test5 = false;
        }
        System.out.println();

        // 6
        // Unifying two cities
        System.out.println("Unifying City1 and City2...");
        City unifiedCity = country.unifyCities("City1", "City2");
        System.out.println("Unified city:");
        if (unifiedCity.getCityName().equals("City1-City2")) {
            System.out.println("PASS: " + unifiedCity.getCityName() + " (Expected: City1-City2)");
            test6 = true;
        } else {
            System.out.println("FAIL: " + unifiedCity.getCityName() + " (Expected: City1-City2)");
            test6 = false;
        }
        System.out.println();

        // 7
        // Check the maximum difference in establishment dates between any two cities
        int maxDiff = country.establishMaxDiff();
        System.out.println("Maximum difference in establishment dates:");
        if (maxDiff == 1189) {
            System.out.println("PASS: " + maxDiff + " (Expected: 4)");
            test7 = true;
        } else {
            System.out.println("FAIL: " + maxDiff + " (Expected: 4)");
            test7 = false;
        }
        System.out.println();

        if (test1 && test2 && test3 && test4 && test5 && test6 && test7) {
            System.out.println("####_CONGRATS!_#### All tests passed!");
        } else {
            System.out.println("Some tests failed!");
        }
        System.out.println();
    }
}
