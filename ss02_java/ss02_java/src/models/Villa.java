package models;

public class Villa extends Facility {
    private String roomStandard;
    private  double areaPool;
    private int floor;

    public Villa() {
    }

    public Villa(String idFacility, String nameService, double areaUse, int rentalPrice, int rentalPeopleMax, String styleRental, String roomStandard, double areaPool, int floor, String roomStandard1, double areaPool1, int floor1) {
        super(idFacility, nameService, areaUse, rentalPrice, rentalPeopleMax, styleRental, roomStandard, areaPool, floor);
        this.roomStandard = roomStandard1;
        this.areaPool = areaPool1;
        this.floor = floor1;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    @Override
    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    @Override
    public double getAreaPool() {
        return areaPool;
    }

    @Override
    public void setAreaPool(double areaPool) {
        this.areaPool = areaPool;
    }

    @Override
    public int getFloor() {
        return floor;
    }

    @Override
    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Villa{" +
                "roomStandard='" + roomStandard + '\'' +
                ", areaPool=" + areaPool +
                ", floor=" + floor +
                ", rentalPrice=" + rentalPrice +
                ", rentalPeopleMax=" + rentalPeopleMax +
                ", styleRental='" + styleRental + '\'' +
                ", roomStandard='" + roomStandard + '\'' +
                ", areaPool=" + areaPool +
                ", floor=" + floor +
                '}';
    }
}
