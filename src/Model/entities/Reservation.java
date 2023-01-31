package Model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import Model.exception.DomainException;

public class Reservation {
	private Integer numberRooms;
	private Date checkIn;
	private Date checkOut;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer numberRooms, Date checkIn, Date checkOut)  {
		if(checkOut.after(checkIn)) {
	       	  throw new DomainException("Check-out date must be after");
	         }
	         
		this.numberRooms = numberRooms;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	public Reservation() {

	}

	public Integer getNumberRooms() {
		return numberRooms;
	}

	public void setNumberRooms(Integer numberRooms) {
		this.numberRooms = numberRooms;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public long duration() {
		long diff = checkOut.getTime() - checkIn.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void updateDate(Date checkIn, Date checkOut)  {
		 Date now = new Date();
         if(checkIn.before(now) || checkOut.before(now)) {
       	  throw new DomainException("Reservation dates for update");
         }
         if(checkOut.after(checkIn)) {
       	  throw new DomainException("Check-out date must be after");
         }
         
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		
	}

	@Override
	public String toString() {
		return "Room " + numberRooms + ", Check-in " + sdf.format(checkIn) + ", Check-out " + sdf.format(checkOut) + ", "
				+ duration() + " nigths";
	}

}
