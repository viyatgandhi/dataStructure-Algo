import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class lyftDetourDistance {
	
	/*
	 * Lyft Coding Challenge
	 * 
	 * Calculate the detour distance between two different rides. 
	 * Given four latitude / longitude pairs, where driver one is traveling from point A to point B 
	 * and driver two is traveling from point C to point D, write a function (in your language of choice) 
	 * to calculate the shorter of the detour distances the drivers would need to take to pick-up and 
	 * drop-off the other driver.
	 * 
	 * Programming is done in java 
	 * Haversine formula is used to calculate the distance
	 */
	
	public static class GeoPoint {
		
		private double latitude;
		private double longitude;
		
		public GeoPoint (double latitude, double longitude){
			this.latitude = latitude;
			this.longitude = longitude;
		}
		
	} // class GeoPoints	
	
	static List<String> possiblePaths = new ArrayList<String>();
	
	static HashMap<String, GeoPoint> location = new HashMap<String, GeoPoint>();
	
	// give appropriate co-ordinates to get detour distance
	
	private static GeoPoint A = new GeoPoint(19, -169) ,
			B = new GeoPoint(-57, 63), 
			C = new GeoPoint(60,-13), 
			D = new GeoPoint(83, 150);
	
	public static double Haversine (GeoPoint source, GeoPoint destination){
		
		// Haversine Formula
		// References : http://rosettacode.org/wiki/Haversine_formula#Java

		final double R  = 3959.87; // radius of earth in miles

		double dLat = Math.toRadians(destination.latitude - source.latitude);
		double dLon = Math.toRadians(destination.longitude - source.longitude);
		double lat1 = Math.toRadians(source.latitude);
		double lat2 = Math.toRadians(destination.latitude);		
		
		double a = Math.pow(Math.sin(dLat/2) , 2) + Math.pow(Math.sin(dLon/2), 2)* Math.cos(lat1)* Math.cos(lat2);
		double c  = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double distance = R * c;
		
		//System.out.println(distance);

		return distance;
	}	
	
	public static void main(String[] args) {

		// adding possible paths to arraylist
		possiblePaths.add("CABD");
		possiblePaths.add("ACDB");

		// adding all location in Hashmap
		location.put("A", A);
		location.put("B", B);
		location.put("C", C);
		location.put("D", D);
		
		ArrayList<Double> calDistance = new ArrayList<Double>();

		// calculating distance for each possible path using Haversine formula
		for(String eachGeoPoint : possiblePaths ){
			char[] geoPoint = eachGeoPoint.toCharArray();
			double currentDistance = 0.0;
			for(int i=0; i<geoPoint.length-1; i++){
				String source = ""+geoPoint[i];
				String destination = ""+geoPoint[i+1];
				currentDistance += Haversine(location.get(source), location.get(destination));				
			}
			calDistance.add(currentDistance);
			//System.out.println(currentDistance);
		}
		
		// finding minimum distance and best path		
		double leastDistance = calDistance.get(0);
		String bestPath = possiblePaths.get(0);
		
		for (int i=0; i<calDistance.size(); i++) {
			if (leastDistance>calDistance.get(i)) {
				bestPath = possiblePaths.get(i);
				leastDistance = calDistance.get(i);
			}
		}
		
		System.out.println("Shorter Detour Distance is: "+ Math.round(leastDistance)+" miles");
		System.out.println("Shorter Detour Path is: "+bestPath);		
		
	} // main
	
} //class lyftDetourDistance
