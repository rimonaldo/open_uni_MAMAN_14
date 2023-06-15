public class CityNode {

    private City _city;
    private CityNode _next;

    public CityNode(City c) {
        // Avoid aliasing by creating a new City
        this._city = new City(c);
        this._next = null;
    }

    public CityNode(City c, CityNode next) {
        // Avoid aliasing by creating a new City
        this._city = new City(c);
        // Here aliasing is not an error
        this._next = next;
    }

    public CityNode(CityNode c) {
        // Avoid aliasing by creating a new City
        this._city = new City(c._city);
        // Here aliasing is not an error
        this._next = c._next;
    }

    public City getCity() {
        // Return a copy to avoid aliasing
        return new City(this._city);
    }

    public CityNode getNext() {
        // Here aliasing is not an error
        return this._next;
    }

    public void setCity(City c) {
        // Avoid aliasing by creating a new City
        this._city = new City(c);
    }

    public void setNext(CityNode next) {
        // Here aliasing is not an error
        this._next = next;
    }




}
