public class Country {
    CityNode _head;
    String _name;

    /**
     * Constructor for objects of class Country.
     * 
     * @param name String name of the country
     */
    public Country(String name) {
        this._name = name;
        this._head = null;
    }

    /**
     * Add a new city to the country's list of cities.
     * The list is sorted by the date established.
     * If the city already exists, the function returns false.
     * 
     * @param cityName           String name of the city
     * @param dayEstablished     int day the city was established
     * @param monthEstablished   int month the city was established
     * @param yearEstablished    int year the city was established
     * @param centerX            int x coordinate of the city's center
     * @param centerY            int y coordinate of the city's center
     * @param stationX           int x coordinate of the city's central station
     * @param stationY           int y coordinate of the city's central station
     * @param numOfResidents     long number of residents in the city
     * @param numOfNeighborhoods int number of neighborhoods in the city
     * 
     * @return true if the city was added successfully, false otherwise.
     */
    public boolean addCity(
            String cityName,
            int dayEstablished, int monthEstablished, int yearEstablished,
            int centerX, int centerY,
            int stationX, int stationY,
            long numOfResidents, int numOfNeighborhoods) {

        // Check if the city already exists
        if (getCityNodeIndex(cityName) != -1) {
            return false;
        }

        // Set city's parameters
        Date dateEstablished = new Date(dayEstablished, monthEstablished, yearEstablished);
        Point center = new Point(centerX, centerY);
        Point station = new Point(stationX, stationY);

        // Build the new city
        City newCity = cityBuilder(cityName, dateEstablished, center, station, numOfResidents, numOfNeighborhoods);

        // Create a new city node
        CityNode newCityNode = new CityNode(newCity);

        // If the list is empty
        // set the new city node as the head
        if (_head == null) {
            _head = newCityNode;
            return true;
        }

        // set nodes for the loop:
        // prev is the node before the runner.
        CityNode prev = _head;
        CityNode runner = _head.getNext();

        // Loop through the list,
        // set the new city node at its place sorted by date established
        while (runner != null) {
            Date runnerDateEstablished = runner.getCity().getDateEstablished();
            if (newCity.getDateEstablished().before(runnerDateEstablished)) {
                prev.setNext(newCityNode);
                newCityNode.setNext(runner);
                return true;
            }
            prev = prev.getNext();
            runner = runner.getNext();
        }

        // If the new city node is the last one
        // set it as the last one
        prev.setNext(newCityNode);
        return true;
    }

    /**
     * Remove a city from the country's list of cities.
     * 
     * @param cityName String name of the city
     * 
     * @return true if the city was removed successfully, false otherwise.
     */
    public long getNumOfResidents() {
        CityNode runner = _head;
        long numOfResidents = 0;

        while (runner != null) {
            numOfResidents += runner.getCity().getNumOfResidents();
            runner = runner.getNext();
        }

        return numOfResidents;
    }

    /**
     * Get the city with the most residents in the country.
     * 
     * @return the city with the most residents in the country.
     */
    public double longestDistance() {
        CityNode prev = _head;
        CityNode runner = _head.getNext();
        double longestDistance = 0;
        // Loop through the list
        // for each city, loop through the rest of the list
        // and check the distance between the two cities.

        // If the distance is longer than the longest distance
        // set the new distance as the longest distance
        // and continue to the next city.

        while (prev != null) {
            Point prevLocPoint = prev.getCity().getCityCenter();
            while (runner != null) {
                Point runnerLocPoint = runner.getCity().getCityCenter();
                if (prevLocPoint.distance(runnerLocPoint) > longestDistance) {
                    longestDistance = prevLocPoint.distance(runnerLocPoint);
                }
                runner = runner.getNext();
            }
            prev = prev.getNext();
        }

        if (longestDistance < 2) {
            return 0;
        }
        return longestDistance;
    }

    /**
     * Get the number of cities in the country that are located north of a given
     * city.
     * (A city is considered north of another city if its center is above the other
     * city's center)
     * 
     * @param cityName String name of the city
     * 
     * @return the number of cities in the country that are located north of a given
     *         city.
     */
    public int numCitiesNorthOf(String cityName) {
        Point cityCenter = getCityByName(cityName).getCityCenter();

        int numOfNotrthenCities = 0;
        CityNode runner = _head;
        while (runner != null) {
            if (runner.getCity().getCityCenter().isAbove(cityCenter)) {
                numOfNotrthenCities++;
            }
            runner = runner.getNext();
        }
        return numOfNotrthenCities;
    }

    /**
     * 
     * @return the southernmost city in the country. (the city with the lowest y)
     */
    public City southernmostCity() {
        CityNode runner = _head;
        City southernmostCity = _head.getCity();
        Point runnerCityCenter = runner.getCity().getCityCenter();
        while (runner != null) {
            if (runnerCityCenter.isUnder(southernmostCity.getCityCenter())) {
                southernmostCity = runner.getCity();
            }
            runner = runner.getNext();
        }

        return southernmostCity;

    }

    /**
     * 
     * @return the name of the country.
     */
    public String getCountryName() {
        return _name;
    }

    /**
     * 
     * @return the number of cities in the country.
     */
    public int getNumOfCities() {
        int numOfCities = 0;

        CityNode runner = _head;
        while (runner != null) {
            numOfCities++;
            runner = runner.getNext();
        }

        return numOfCities;
    }

    /**
     * 
     * 
     * @param date1 Date object
     * @param date2 Date object
     * @return True if the country has at least one city that was established before
     *         the prior date and at least one city that was established after the
     *         later date.
     */
    public Boolean wereCitiesEstablishedBeforeOrAfter(Date date1, Date date2) {
        Date priorDate, laterDate;
        if (date1.before(date2)) {
            priorDate = date1;
            laterDate = date2;
        } else {
            priorDate = date2;
            laterDate = date1;
        }

        CityNode runner = _head;
        while (runner != null) {
            if (runner.getCity().getDateEstablished().before(priorDate) ||
                    runner.getCity().getDateEstablished().after(laterDate)) {
                return true;

            }
            runner = runner.getNext();
        }
        return false;
    }

    /**
     * Get the city with the most residents in the country.
     * 
     * @param cityName1
     * @param cityName2
     * @return the city with the most residents in the country.
     */
    public City unifyCities(String cityName1, String cityName2) {
        // Get the cities to unify.
        City city1 = getCityByName(cityName1);
        City city2 = getCityByName(cityName2);

        // Prepare the data for the new city.
        String unifiedCityName = cityName1 + "-" + cityName2;
        long unifiedCityNumOfRes = city1.getNumOfResidents() + city2.getNumOfResidents();
        int unifiedCityNumOfNeighs = city1.getNumOfNeighborhoods() + city2.getNumOfNeighborhoods();
        Point unifiedCityCenterPoint = city1.getCityCenter().middle(city2.getCityCenter());
        Point unifiedCityCentralStationPoint;
        Date unifiedCityEstablishedDate;

        // Declare nodes.
        CityNode nodeToUpdate;
        CityNode nodeToDelete;

        // set the unified city's established date, and the node to update
        // as the older city date established.
        // set the node to delete as the newer city by date established.
        if (city1.getDateEstablished().before(city2.getDateEstablished())) {
            unifiedCityEstablishedDate = city1.getDateEstablished();
            nodeToUpdate = getCityNodeByName(cityName1);
            nodeToDelete = getCityNodeByName(cityName2);
        } else {
            unifiedCityEstablishedDate = city2.getDateEstablished();
            nodeToUpdate = getCityNodeByName(cityName2);
            nodeToDelete = getCityNodeByName(cityName1);
        }

        // set the unified city's central station as the leftmost station.
        if (city1.getCentralStation().isLeft(city2.getCentralStation())) {
            unifiedCityCentralStationPoint = city1.getCentralStation();
        } else {
            unifiedCityCentralStationPoint = city2.getCentralStation();
        }

        // Update the city to update with the new data.
        City cityToUpdate = nodeToUpdate.getCity();
        cityToUpdate.setCityName(unifiedCityName);
        cityToUpdate.setDateEstablished(unifiedCityEstablishedDate);
        cityToUpdate.setNumOfResidents(unifiedCityNumOfRes);
        cityToUpdate.setNumOfNeighborhoods(unifiedCityNumOfNeighs);
        cityToUpdate.setCityCenter(unifiedCityCenterPoint);
        cityToUpdate.setCentralStation(unifiedCityCentralStationPoint);

        // Set the node to update's city as the unified city.
        nodeToUpdate.setCity(cityToUpdate);

        // Set the city to delete's next node as the city to update's next node.
        CityNode prev = _head;
        CityNode runner = _head.getNext();
        String runnerCityName;
        String nodeToDeleteCityName = nodeToDelete.getCity().getCityName();

        while (runner != null) {
            runnerCityName = runner.getCity().getCityName();
            if (runnerCityName.equals(nodeToDeleteCityName)) {
                prev = nodeToUpdate;
                prev.setNext(runner.getNext());
                break;
            }
            prev = prev.getNext();
            runner = runner.getNext();
        }
        return nodeToUpdate.getCity();
    }

    /**
     * Get the largest difference in days between the establishment dates of two
     * cities in the country.
     * 
     * @return the largest difference in days between the establishment dates of two
     */
    public int establishMaxDiff() {
        int maxDiffInDays = 0;

        CityNode prev = _head;
        CityNode runner = _head.getNext();

        while (prev != null) {
            Date prevNodeDate = prev.getCity().getDateEstablished();
            while (runner != null) {
                Date runnerDateToCompare = runner.getCity().getDateEstablished();
                if (prevNodeDate.difference(runnerDateToCompare) > maxDiffInDays) {
                    maxDiffInDays = prev.getCity().getDateEstablished().difference(runnerDateToCompare);
                }
                runner = runner.getNext();
            }
            prev = prev.getNext();
        }
        return maxDiffInDays;
    }

    // private methods

    private City cityBuilder(String CityName, Date dateEstablished, Point center, Point station, long numOfResidents,
            int numOfNeighborhoods) {
        City newCity = new City(CityName, dateEstablished.getDay(), dateEstablished.getMonth(),
                dateEstablished.getYear(), center.getX(), center.getY(), station.getX(), station.getY(), numOfResidents,
                numOfNeighborhoods);
        return newCity;
    }

    private int getCityNodeIndex(String cityName) {
        CityNode runner = _head;
        int index = 0;
        while (runner != null) {
            if (runner.getCity().getCityName().equals(cityName)) {
                return index;
            }
            runner = runner.getNext();
            index++;
        }
        return -1;
    }

    private City getCityByName(String cityName) {
        CityNode prev = _head;

        while (!cityName.equals(prev.getCity().getCityName())) {
            prev = prev.getNext();
        }
        return prev.getCity();
    }

    private CityNode getCityNodeByName(String cityName) {
        CityNode prev = _head;

        while (!cityName.equals(prev.getCity().getCityName())) {
            prev = prev.getNext();
        }
        return prev;
    }

}
