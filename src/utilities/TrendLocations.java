package utilities;

import java.util.TreeSet;

import twitter4j.Location;
import twitter4j.ResponseList;

public class TrendLocations {

	/***/
	private TreeSet<TrendLocation> locations;


	/*****************************************************************

	 *****************************************************************/
	public TrendLocations (ResponseList<Location> locals) {

		locations = new TreeSet<TrendLocation>();

		for (Location loc: locals) {
			locations.add(new TrendLocation(loc));
		}
	}

	/*****************************************************************

	 *****************************************************************/
	public TreeSet<TrendLocation> getLocations() {
		return locations;
	}
	
	/*****************************************************************

	@return TrendLocation[]
	 *****************************************************************/
	public TrendLocation[] getArray() {		
		TrendLocation[] tlArray = new TrendLocation[locations.size()];
		tlArray = locations.toArray(tlArray);
		return tlArray;
	}

	/*****************************************************************


	 *****************************************************************/
	public void printTrendLocations () {	
		System.out.println(locations.size());
		System.out.println(locations);
	}

	/*****************************************************************

	 *****************************************************************/
	public class TrendLocation implements Comparable{

		/***/
		private String countryCode;

		/***/
		private String compareName;

		/***/
		private Location locationTrend;

		/*****************************************************************

		 *****************************************************************/
		public TrendLocation(String code) {
			countryCode = code;
		}

		/*****************************************************************


		 *****************************************************************/
		public TrendLocation(Location place) {
			locationTrend = place;
			countryCode = locationTrend.getCountryName();
			if (place.getPlaceCode() != 7) {
				compareName = place.getCountryName();
			} else {
				compareName = place.getCountryName() + " " + place.getName();
			}
		}

		/*****************************************************************

		@return String
		 *****************************************************************/
		public String getCountryCode () {		
			return countryCode;
		}

		/*****************************************************************

		@param country Location
		 *****************************************************************/
		public void setLocation (Location loc) {		
			locationTrend = loc;
		}

		/*****************************************************************

		@return String
		 *****************************************************************/
		public String getName () {		
			return locationTrend.getName();
		}

		/*****************************************************************

		@return String
		 *****************************************************************/
		public String getTownName () {		
			return locationTrend.getName() + ", " + locationTrend.getCountryName();
		}

		/*****************************************************************

		@return Location
		 *****************************************************************/
		public Location getLocation () {		
			return locationTrend;
		}

		public String toString() {
			return getName() + ":" + locationTrend.getPlaceCode() + "\n";
		}

		/*****************************************************************

		@return boolean
		 *****************************************************************/
		public boolean equals (Object obj) {		
			return countryCode.equals(obj);
		}

		/*****************************************************************

		 *****************************************************************/
		//a negative integer (this < arg0), 
		//zero (this== arg0), or 
		//a positive integer(this > arg0)
		@Override
		public int compareTo(Object arg0) {

			TrendLocation temp = (TrendLocation) arg0;
			if (temp.getCountryCode() == null) {
				//System.out.println("Temp Code Null");
				return 1;
			}
			if (locationTrend.getPlaceCode() == 19) {
				return -1;
			}
			if (temp.getLocation().getPlaceCode() == 19) {
				return 1;
			}

			return compareName.compareTo(temp.compareName);
			
		}

	}

}
