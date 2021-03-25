package service.models;

public class Point {

    int Id;
    String coordinates;
    String description;

    public Point () {}

    public Point (int Id, String coordinates, String description) {
        this.Id = Id;
        this.coordinates = coordinates;
        this.description = description;
    }

    public void setId(int id) { Id = id; }
    public int getId() { return Id; }

    public void setCoordinates(String coordinates) { this.coordinates = coordinates; }
    public String getCoordinates() { return coordinates; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }
}
