package pe.model;

import java.math.BigDecimal;
import java.util.Date;

public class RoomForRentDto {
    private int id;
    private String title;
    private BigDecimal price;
    private String location;
    private String description;
    private Date postedDate;
    private int status;
    private String username;

    public RoomForRentDto() {
    }

    public RoomForRentDto(int id, String title, BigDecimal price, String location, String description, Date postedDate, int status, String username) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.location = location;
        this.description = description;
        this.postedDate = postedDate;
        this.status = status;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
