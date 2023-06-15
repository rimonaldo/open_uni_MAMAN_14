public class City {
    private String _cityName;
    private Date _dateEstablished;
    private Point _cityCenter;
    private Point _centralStation;
    private long _numOfResidents;
    private int _numOfNeighborhoods;
    private final int MIN_RESIDENTS = 0;
    private final int MIN_NEIGHBORHOODS = 1;

    public City(final String cityName, final int dayEstablished, final int monthEstablished, final int yearEstablished,
            final int centerX, final int centerY, final int stationX, final int stationY, final long numOfResidents,
            final int noOfNeighborhoods) {
        this._cityName = cityName;
        this._dateEstablished = new Date(dayEstablished, monthEstablished, yearEstablished);
        this._cityCenter = new Point(centerX, centerY);
        this._centralStation = new Point(stationX, stationY);
        this._numOfResidents = ((numOfResidents < 0L) ? 0L : numOfResidents);
        this._numOfNeighborhoods = ((noOfNeighborhoods <= 1) ? 1 : noOfNeighborhoods);
    }

    public City(final City other) {
        this._cityName = other._cityName;
        this._dateEstablished = new Date(other._dateEstablished);
        this._cityCenter = new Point(other._cityCenter);
        this._centralStation = new Point(other._centralStation);
        this._numOfResidents = other._numOfResidents;
        this._numOfNeighborhoods = other._numOfNeighborhoods;
    }

    public String getCityName() {
        return this._cityName;
    }

    public Date getDateEstablished() {
        return new Date(this._dateEstablished);
    }

    public Point getCityCenter() {
        return new Point(this._cityCenter);
    }

    public Point getCentralStation() {
        return new Point(this._centralStation);
    }

    public long getNumOfResidents() {
        return this._numOfResidents;
    }

    public int getNumOfNeighborhoods() {
        return this._numOfNeighborhoods;
    }

    public void setCityName(final String cityName) {
        this._cityName = cityName;
    }

    public void setDateEstablished(final Date dateEst) {
        this._dateEstablished = new Date(dateEst);
    }

    public void setCityCenter(final Point cityCenter) {
        this._cityCenter = new Point(cityCenter);
    }

    public void setCentralStation(final Point centralStation) {
        this._centralStation = new Point(centralStation);
    }

    public void setNumOfResidents(final long numOfResidents) {
        if (numOfResidents >= 0L) {
            this._numOfResidents = numOfResidents;
        }
    }

    public void setNumOfNeighborhoods(final int noOfNeighborhoods) {
        if (noOfNeighborhoods >= 1) {
            this._numOfNeighborhoods = noOfNeighborhoods;
        }
    }

    @Override
    public String toString() {
        return "City name: " + this._cityName + "\nDate established: " + this._dateEstablished + "\nCity center: "
                + this._cityCenter + "\nCentral station: " + this._centralStation + "\nNumber of residents: "
                + this._numOfResidents + "\nNumber of neighborhoods: " + this._numOfNeighborhoods;
    }

    public boolean equals(final City other) {
        return this._cityName.equals(other._cityName) && this._dateEstablished.equals(other._dateEstablished)
                && this._cityCenter.equals(other._cityCenter) && this._centralStation.equals(other._centralStation)
                && this._numOfResidents == other._numOfResidents
                && this._numOfNeighborhoods == other._numOfNeighborhoods;
    }

    public boolean addResidents(final long residentsUpdate) {
        if (this._numOfResidents + residentsUpdate < 0L) {
            this.setNumOfResidents(0L);
            return false;
        }
        this.setNumOfResidents(this._numOfResidents + residentsUpdate);
        return true;
    }

    public void moveCentralStation(final int deltaX, final int deltaY) {
        this._centralStation.move(deltaX, deltaY);
    }

    public double distanceBetweenCenterAndStation() {
        return this._cityCenter.distance(this._centralStation);
    }

    public City newCity(final String newCityName, final int dX, final int dY) {
        final Date d = this._dateEstablished.tomorrow();
        final Point center = new Point(this._cityCenter);
        center.move(dX, dY);
        final Point centralStation = new Point(this._centralStation);
        centralStation.move(dX, dY);
        return new City(newCityName, d.getDay(), d.getMonth(), d.getYear(), center.getX(), center.getY(),
                centralStation.getX(), centralStation.getY(), 0L, 1);
    }

    public boolean cityEstablishedBetweenDates(Date date1, Date date2) {
        if (date2.before(date1)) {
            final Date tmp = new Date(date1);
            date1 = date2;
            date2 = tmp;
        }
        return this._dateEstablished.equals(date1) || this._dateEstablished.equals(date2)
                || (this._dateEstablished.after(date1) && this._dateEstablished.before(date2));
    }

    public int establishmentDateDiff(final City other) {
        return this._dateEstablished.difference(other._dateEstablished);
    }
}