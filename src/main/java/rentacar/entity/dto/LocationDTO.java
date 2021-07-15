package rentacar.entity.dto;

public class LocationDTO {
    public String city;

    public String number;

    public String street;

    public int manager;

    public String image;

    public String getCity() {
        return city;
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public int getManager() {
        return manager;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
