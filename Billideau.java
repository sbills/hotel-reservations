import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Billideau {




	public class CS225_Project5_Client extends Hotel implements Serializable
	{


		

		 ArrayList<Hotel> hotelList = new ArrayList<Hotel>();
		 ArrayList<Reservation> resList = new ArrayList<Reservation>();

		public  void main(String[] args) throws IOException {
			System.out.println("Sean Billideau");
			System.out.println("Project 5");
			System.out.println("");


			readHotel();
			for (Hotel i : hotelList) {
				System.out.println(i.uniqueId);
				System.out.println(i.hotelName);
				System.out.println(i.streetAddress);
				System.out.println(i.city);
				System.out.println(i.stateAbbreviation);
				System.out.println(i.pricePerNight);
				System.out.println(" ");

			}
			System.out.println("What hotel would you like to stay in?");

			readReservation();
			makeReservation();
			writeReservation();




		}

		public  void makeReservation(){
			int chosenNumber = 0;
			int arrivingDay = 0;
			int arrivingMonth = 0;
			int leavingDay = 0;
			int leavingMonth = 0;
			boolean condition = true;
			while(condition = true)
			{
				Scanner scan = new Scanner(System.in);
				chosenNumber = scan.nextInt();
				System.out.println("Please choose the month you will be arriving");
				arrivingMonth = scan.nextInt();
				System.out.println("Please choose the day you will be arriving");
				arrivingDay = scan.nextInt();
				System.out.println("Please choose the month you will be leaving");
				leavingMonth = scan.nextInt();
				System.out.println("Please choose the day you will be leaving");
				leavingDay = scan.nextInt();
				if(arrivingMonth == leavingMonth)
					condition = false;
				if(arrivingMonth != leavingMonth)
					System.out.println("Please keep the month the same for arrival and departure");

				for(Reservation r: resList)
					if( chosenNumber == r.chosenHotel ){


						if(arrivingMonth == r.arrivingMonth){

							if(arrivingDay < r.leavingDay && arrivingDay == r.arrivingDay){
								condition = false;


							}	
						}
					}
				System.out.println("Days already booked please choose different dates");
				System.out.println("Please choose a hotel number");
				//	System.out.println("Please keep the month the same for arrival and departure");
				//}
			}

			Reservation res = new Reservation(chosenNumber, arrivingDay, arrivingMonth, leavingDay, leavingMonth);
			resList.add(res);
			System.out.println("Your reservation was made");
			System.out.println("You will be paying " + (pricePerNight * leavingDay - arrivingDay) );


		}

		public  void readReservation(){
			try
			{

				FileInputStream inputFileStream = new FileInputStream("Reservations.txt");
				ObjectInputStream objectInputStream = new ObjectInputStream(inputFileStream);
				try
				{
					while( true)
					{
						Reservation res = (Reservation) objectInputStream.readObject();
						resList.add(res);
						//System.out.println("kjhgjlh");
					}
				}
				catch (EOFException eof)
				{				
				}

				catch(ClassNotFoundException e)
				{
					e.printStackTrace();
				}

				finally
				{
					try {
						objectInputStream.close();

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			catch (EOFException eof)
			{

			}
			catch(FileNotFoundException fnfe)
			{
				System.out.println("broken");
			}
			catch(IOException i)
			{
				i.printStackTrace();
			}
		}

		public  void readHotel(){
			try
			{	
				File fs = new File("Hotels.txt");

				Scanner file = new Scanner(fs);
				while (file.hasNextLine()){

					file.useDelimiter("\\(|, |\\)");
					Hotel temp = new Hotel();
					try{ //populate arraylist
						file.next();
						temp.uniqueId = file.nextInt();
						temp.hotelName = file.next();
						temp.streetAddress = file.next();
						temp.city = file.next();
						temp.stateAbbreviation = file.next();
						temp.pricePerNight = file.nextDouble();
					} catch (InputMismatchException e) {
						System.out.println("bad input");
					}
					hotelList.add(temp);


					if(file.hasNextLine())
						file.nextLine();
				}
				file.close();

			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

		}

		public  void writeReservation(){
			try{

				FileOutputStream fileOutputStream = new FileOutputStream("Reservations.txt");
				ObjectOutputStream objectOutputStream  = new ObjectOutputStream(fileOutputStream);
				for( Reservation i: resList )
				{
					objectOutputStream.writeObject(i);
				}

				objectOutputStream.close();

				fileOutputStream.close();
			}
			catch(IOException i)
			{ 
				i.printStackTrace();
			}

			catch(Exception e)
			{
				e.printStackTrace();
			}

		}

		public   class Hotel
		{



			// Your program should always output your name and the project number.
			// DO NOT DELETE OR COMMENT OUT. Replace with relevant info.

			int uniqueId;
			String hotelName;
			String streetAddress;
			String city;
			String stateAbbreviation;
			Double privateCheckout;
			Double pricePerNight;
			// Your code should go below this line
			Hotel(){
				uniqueId = 0;
				hotelName = "";
				streetAddress = "";
				city = "";
				stateAbbreviation = "";
				privateCheckout = 0.0;
				pricePerNight = 0.0;
				//Hotel(uniqueId, hotelName, pricePerNight, streetAddress, city, stateAbbreviation, privateCheckout);
			}

		}


		public  class Reservation implements Serializable
		{

			int chosenHotel;
			int arrivingMonth;
			int arrivingDay;
			int leavingMonth;
			int leavingDay;

			Reservation(){
				chosenHotel = 0;
				arrivingMonth = 0;
				arrivingDay = 0;
				leavingMonth = 0;
				leavingDay = 0;

			}

			Reservation(int chosen, int day, int month, int day2, int month2){
				chosenHotel = chosen;
				arrivingDay = day;
				arrivingMonth = month;
				leavingDay = day2;
				leavingMonth = month2;
			}



		}

	}
}





